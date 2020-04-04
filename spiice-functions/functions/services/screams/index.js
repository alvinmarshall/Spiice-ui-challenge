const express = require("express");
const router = express.Router();
const tokenAuth = require("../../utils/token-utils");
const controller = require("./screams.controller");

router.get("/", controller.index);
router.post("/create", tokenAuth, controller.create);

module.exports = router;
