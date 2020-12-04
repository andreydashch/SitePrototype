<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="CSS/index.css">
</head>
<body>
    <header>
        <div class="logo nav-el">
            PhotoBank
        </div>
    </header>

    <main>
        <#assign add_photo>
                <div class="photo add-area--wrapper">
                    <input type="file" id="add-photo--input" class="add-photo--input" multiple onchange="addFiles(this.files)">
                    <label for="add-photo--input" class="add-area add_photo--label">
                        <div class="plus vertical-bock"></div>
                        <div class="plus horizontal-bock"></div>
                    </label>
                </div>
        </#assign>

        <#if !isCurrentDate>
            <div class="date">
                ${currentDate}
            </div>

            <div class="photo-container" id="photo-container">
                ${add_photo}
            </div>
        </#if>

        <#list user.getDates()?reverse as date>
            <div class="date">
                ${date.getDate()}
            </div>

            <div class="photo-container" id="photo-container">
                <#if date.getDate() == currentDate>
                    ${add_photo}
                </#if>

                <#list date.getPhotos() as photo>
                    <div class="photo">
                        <img src=${"SavedImages/" + photo.getSrc() + ".jpg"}>
                    </div>
                </#list>
            </div>
        </#list>
    </main>

    <footer></footer>
</body>
<script type="text/javascript" src="JS/index.js"></script>
</html>