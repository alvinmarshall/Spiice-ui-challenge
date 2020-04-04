const express = require("express");
const router = express.Router();
const tokenAuth = require("../../utils/token-utils");
const controller = require("./reviews.controller");

router.get("/", controller.index);
router.post("/", tokenAuth, tokenAuth, controller.create);

module.exports = router;
