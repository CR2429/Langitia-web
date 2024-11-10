function initClient() {
    gapi.client.init({
        'apiKey': 'AIzaSyDkNbxcAp6fQZUQ5V5tEe3wjTEK5pueRts',
        'clientId': '637161977807-dijojskm8287vjfjbhvidmo7vureshqc.apps.googleusercontent.com',
        'scope': 'https://www.googleapis.com/auth/spreadsheets.readonly',
        'discoveryDocs': ['https://sheets.googleapis.com/$discovery/rest?version=v4']
    }).then(function () {
        AppelJSON("spreadshe1IP76S8HrvHM6wIOfIs77-xhxk8-DJci9svKY44O2jwwetId","SC!B2:E1000");
    });
}

function handleClientLoad() {
    gapi.load('client:auth2', initClient);
}

window.onload = handleClientLoad;

function AppelJSON(spreadsheetId, range, callback) {
    try {
        gapi.client.sheets.spreadsheets.values.get({
          spreadsheetId: spreadsheetId,
          range: range,
        }).then((response) => {
          const result = response.result;
          const numRows = result.values ? result.values.length : 0;
          console.log(`${numRows} rows retrieved.`);
          if (callback) callback(response);
        });
      } catch (err) {
        document.getElementById('body-div').innerText = err.message;
        return;
      }
}