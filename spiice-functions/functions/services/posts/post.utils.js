module.exports = PostUtils = {
  extractUserRefDoc: (result,key) => {
    const aPromise = item => {
      return Promise.resolve(item);
    };
    const userRefAsync = async docs => {
      const doc = docs.data();
      const userRef = doc[key].ref;
      const _ = await userRef.get();
      doc[key] = _.data();
      doc.id = docs.id;
      return aPromise(doc);
    };

    const getData = async result => {
      return Promise.all(result.docs.map(doc => userRefAsync(doc)));
    };
    return getData(result);
  }
};
