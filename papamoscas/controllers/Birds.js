'use strict';

var url = require('url');


var Birds = require('./BirdsService');


module.exports.birdsGet = function birdsGet (req, res, next) {
  Birds.birdsGet(req.swagger.params, res, next);
};

module.exports.birdsPut = function birdsPut (req, res, next) {
  Birds.birdsPut(req.swagger.params, res, next);
};

module.exports.birdsPost = function birdsPost (req, res, next) {
  Birds.birdsPost(req.swagger.params, res, next);
};

module.exports.birdsDelete = function birdsDelete (req, res, next) {
  Birds.birdsDelete(req.swagger.params, res, next);
};

module.exports.birdsIdGet = function birdsIdGet (req, res, next) {
  Birds.birdsIdGet(req.swagger.params, res, next);
};
