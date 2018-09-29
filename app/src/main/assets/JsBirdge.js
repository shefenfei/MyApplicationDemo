var Dog = {
  createDog:function(){
    var dog = {};
    dog.name = "汪汪";
    dog.sayHello = function(){
      console.log("Hello World!");
    };
    return dog;
  }
};
var dog = Dog.createDog();
dog.sayHello();