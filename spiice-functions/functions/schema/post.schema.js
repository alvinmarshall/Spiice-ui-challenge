module.exports = postSchema = {
  header: "header",
  description: "description",
  amount: "$ 200",
  skills: ["skill 1", "skill 2"],
  user:{
      ref:"doc/users",
      email:'email@me.com'
  },
  createdAt:'2020-03-31T00:40:16.131Z'
};
