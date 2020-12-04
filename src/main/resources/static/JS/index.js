const addableContainer = document.querySelector(".photo-container");
const addPhotoButton = document.querySelector(".add-area--wrapper");

window.addEventListener(`resize`, processPage, false);

window.onload = function() {
    processPage();
};

function processPage() {
    const containers_list = document.querySelectorAll(".photo-container");

    containers_list.forEach(container => Container.processContainer(container));
}

class Container {
    static previousMargin = 10;

    constructor(container) {
        this.maxLineLength = container.clientWidth;
        this.newLine()
    }

    newLine() {
        this.width = 0;
        this.boxes_list = [];
    }

    addBox(box) {
        const nextLineLength = this.width + box.clientWidth;

        if (nextLineLength <= this.maxLineLength) {
            this.width = nextLineLength;
            this.boxes_list[this.boxes_list.length] = box;
        } else {
            this.autoMargin();
            this.newLine();

            this.addBox(box);
        }
    }

    autoMargin() {
        const freeSpace = this.maxLineLength - this.width;
        let margin = freeSpace / (this.boxes_list.length - 1) - 1;

        if (margin + 1 === freeSpace && margin > Container.previousMargin) {
            margin = Container.previousMargin;
        }

        for (let counter = 0; counter < this.boxes_list.length - 1; counter++) {
            this.boxes_list[counter].style.marginRight = margin.toString() + "px";
        }

        this.boxes_list[this.boxes_list.length - 1].style.marginRight = "0";
        Container.previousMargin = margin;
    }


    static processContainer(objectContainer) {
        const line = new Container(objectContainer);
        const boxes_list = objectContainer.querySelectorAll(".photo");

        boxes_list.forEach(box => line.addBox(box));

        line.autoMargin();
    }
}

function addFiles(files) {

    Promise.all(Array.from(files).map(readAsDataURL))
        .then(pushPhotos)
        .then(imageURLs => imageURLs.forEach(createPhotoEl))
        .catch(console.log);

    function pushPhotos(dataURLsArray) {
        return new Promise(((resolve, reject) => {
            const request = new XMLHttpRequest();

            const body = JSON.stringify(dataURLsArray.map(prepareBodyEl))
                .replace(/%([0-9A-F]{2})/g, (match, p1) => {
                    return String.fromCharCode('0x' + p1);
            });

            request.open('POST','/add-photos',true);
            request.setRequestHeader('Content-Type', 'application/json');

            request.send(body);

            request.onreadystatechange = () => {

                if (request.readyState === 4) {
                    if (request.status === 200) {
                        resolve(dataURLsArray);
                    } else {
                        reject("ServerSavingError");
                    }
                }
            };
        }));

        function prepareBodyEl(dataURL) {
            const imageBase64 = dataURL.toString().split(",")[1];

            return encodeURIComponent(imageBase64);
        }
    }

    function readAsDataURL(file) {
        return new Promise(resolve => {

            const fileReader = new FileReader();
            fileReader.readAsDataURL(file);

            fileReader.onloadend = () => {
                resolve(fileReader.result.toString());
            }
        });
    }

    function createPhotoEl(dataUrl) {
        return new Promise(resolve => {

            const photo = document.createElement("div");
            const image = document.createElement("img");

            photo.append(image);
            photo.classList.toggle("photo");

            image.src = dataUrl;

            addPhotoButton.parentNode.insertBefore(photo, addPhotoButton.nextSibling);

            image.onload = function () {
                Container.processContainer(addableContainer);
                resolve();
            }
        });
    }
}