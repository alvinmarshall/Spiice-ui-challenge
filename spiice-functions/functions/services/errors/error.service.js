module.exports = errorService = {
  isInternalError: (res, errors) => {
    console.error(errors);
    if (!errors.code)
      return res.status(500).send({ message: "Internal error" });
    switch (errors.code) {
      case "auth/user-not-found":
      case "auth/wrong-password":
        return res.status(401).send(authError());
      case "auth/email-already-in-use":
        return res.status(400).send(isInUseError());
      default:
        return res.status(500).send({ message: "Internal error" });
    }
  },

  isClientError: (res, errors) => {
    return res.status(400).send({ errors });
  },

  notAuthorizeError: (res, errors) => {
    console.error(errors.code);
    return res.status(401).send({ message: "Unauthorize", status: 401 });
  }
};

const authError = () => {
  return { message: "username or password invalid", status: 401 };
};

const isInUseError = () => {
  return { message: "sorry, already in use", status: 400 };
};
