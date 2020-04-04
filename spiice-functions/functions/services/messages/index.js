const express = require("express");
const router = express.Router();
const tokenAuth = require("../../utils/token-utils");
const controller = require("./message.controller");

router.get("/", controller.index);
router.get("/sent", tokenAuth, controller.getSentMessages);
router.get("/receiver", tokenAuth, controller.getReceiverMessages);
router.post("/", tokenAuth, controller.create);

module.exports = router;
