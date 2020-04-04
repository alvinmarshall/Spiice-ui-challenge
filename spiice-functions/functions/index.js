const functions = require("firebase-functions");
const admin = require("./libs/firebase.config");
const app = require("express")();
const bodyParser = require("body-parser");
const routes = require("./router");

// MIDDLE WARE
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
routes(app);

module.exports.api = functions.https.onRequest(app);
