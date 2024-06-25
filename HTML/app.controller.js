angular.module('testingApp').controller('MainController', function() {
    this.greeting = 'Hello, AngularJS!';
    
    this.changeGreeting = function() {
        this.greeting = 'Greeting changed!';
    };
});
