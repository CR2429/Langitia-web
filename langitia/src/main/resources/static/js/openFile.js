function openTxtWindow() {
    fetch('/ouvrir-fenetre/txt')
        .then(response => response.text())
        .then(html => {
            let container = document.getElementById('windowContainer');

            // Création de la fenêtre
            let windowDiv = document.getElementById("windowContainer");
            windowDiv.insertAdjacentHTML(html);
        })
        .catch(error => console.error('Erreur:', error));
}

function makeWindowDraggable(windowElement) {
    let isDragging = false, startX, startY, startLeft, startTop;

    const titleBar = windowElement.querySelector('.window-title');
    
    titleBar.onmousedown = function (e) {
        isDragging = true;
        startX = e.clientX;
        startY = e.clientY;
        startLeft = windowElement.offsetLeft;
        startTop = windowElement.offsetTop;
    };

    document.onmousemove = function (e) {
        if (isDragging) {
            let newX = startLeft + (e.clientX - startX);
            let newY = startTop + (e.clientY - startY);
            windowElement.style.left = newX + "px";
            windowElement.style.top = newY + "px";
        }
    };

    document.onmouseup = function () {
        isDragging = false;
    };
}

function closeWindow() {
    document.getElementById("txtWindow").style.display = "none";
}