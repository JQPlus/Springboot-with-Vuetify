import http from "./index.js"
import request from './interception'

/**
 * 
 * @param {Array} files the files wanted to upload
 * @param {String} folderName the file name saved in the server, uniqued to be acknowledged
 * @param {Object} extend includes the request timeout and response type 
 */
function upload(files, folderName, extend = { timeout: 60 * 10 * 1000, responseType: 'json' }) {
    let formData = new FormData();
    // use the formData to load file
    files.forEach(element => {
        formData.append("file", element);
    }); 
    formData.append("folderName", folderName);
    return request({
        url: "file/getFilesAfterUpload",
        method: 'post',
        data: formData,
        responseType: extend.responseType,
        timeout: extend.timeout
    })
}

function download(folderName, fileName, extend = { timeout: 60 * 10 * 1000 }) {
    let formData = {
        fileName,
        folderName
    }
    return request({
        url: "file/downloadFile",
        method: 'post',
        data: formData,
        responseType: 'blob',
        timeout: extend.timeout
    }).then((res) => {
        // create a tage element 'a'
        let oA = document.createElement("a");
        oA.href = window.URL.createObjectURL(
            new Blob([res], { type: "application/octet-stream" })
        );
        //name the dowload file
        oA.download = fileName;
        oA.click();
    });
}

function getFiles(folderName) {
    let files = []
    http.get("file/getFiles", { folderName: folderName })
        .then((res) => {
            files = res
        });
    return files;
}

function remove(folderName, fileName, extend = { timeout: 60 * 10 * 1000 }) {
    let formData = {
        fileName,
        folderName
    }
    return request({
        url: "file/deleteFiles",
        method: 'delete',
        data: formData,
        responseType: 'json',
        timeout: extend.timeout
    }).then((res) => {
        console.log(res);
    });
}

function preview(folderName, fileName, extend = { timeout: 60 * 1000 * 10 }) {
    let formData = {
        fileName,
        folderName
    }
    return request({
        url: "file/preview",
        method: 'post',
        data: formData,
        responseType: 'blob',
        timeout: extend.timeout
    }).then((res) => {
        window.open(getObjectURL(res));
    });
}
function getObjectURL(file) {
    let url = null;
    if (window.createObjectURL != undefined) {
        // basic
        url = window.createObjectURL(file);
    } else if (window.webkitURL != undefined) {
        // webkit or chrome
        try {
            url = window.webkitURL.createObjectURL(file);
        } catch (error) {
            error;
        }
    } else if (window.URL != undefined) {
        // mozilla(firefox)
        try {
            url = window.URL.createObjectURL(file);
        } catch (error) {
            error;
        }
    }
    return url;
}

export default { upload, download, getFiles, remove, preview }