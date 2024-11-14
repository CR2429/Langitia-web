window.addEventListener("load", function () {

    //element file
    fileList = this.document.getElementsByClassName("file");
    console.log(fileList);
    for (let i = 0; i < fileList.length; i++) {
        fileList[i].innerHTML = "<div class=\"fileIcon\"></div><p>test.txt</p>";
    }
  });