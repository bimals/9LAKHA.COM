/**
 * AngularJS Tutorial 1
 * @author Nick Kaye <nick.c.kaye@gmail.com>
 */

/**
 * Main AngularJS Web Application
 */
var app = angular.module('tutorialWebApp', [
  'ngRoute'
]);

/**
 * Configure the Routes
 */
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/", {templateUrl: "resources/partials/home.html", controller: "PageCtrl"})
    // Pages
    .when("/about", {templateUrl: "resources/partials/about.html", controller: "PageCtrl"})
    .when("/faq", {templateUrl: "resources/partials/faq.html", controller: "PageCtrl"})
    .when("/pricing", {templateUrl: "resources/partials/pricing.html", controller: "PageCtrl"})
    .when("/services", {templateUrl: "resources/partials/services.html", controller: "PageCtrl"})
    .when("/contact", {templateUrl: "resources/partials/contact.html", controller: "PageCtrl"})
    // Blog
    .when("/blog", {templateUrl: "resources/partials/blog.html", controller: "BlogCtrl"})
    .when("/blog/post", {templateUrl: "resources/partials/blog_item.html", controller: "BlogCtrl"})
    // else 404
    .otherwise("/404", {templateUrl: "resources/partials/404.html", controller: "PageCtrl"});
}]);

/**
 * Controls the Blog
 */
app.controller('BlogCtrl', function (/* $scope, $location, $http */) {
  console.log("Blog Controller reporting for duty.");
});

/**
 * Controls all other Pages
 */
app.controller('PageCtrl', function (/* $scope, $location, $http */) {
  console.log("Page Controller reporting for duty.");

  // Activates the Carousel
  $('.carousel').carousel({
    interval: 5000
  });

  // Activates Tooltips for Social Links
  $('.tooltip-social').tooltip({
    selector: "a[data-toggle=tooltip]"
  })
});