<!-- FreeMarker Macros-->

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Test</title>

    <link rel="stylesheet" href="/CSS/index.css">
</head>
<body>
    Currency: ${currency.getName()} <br>
    Coefficient: ${currency.coefficientIntegerPart}.${currency.coefficientFloatPart}
</body>
</html>