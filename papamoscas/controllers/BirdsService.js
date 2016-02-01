'use strict';
var examples = {};
  
  examples['application/json'] = [ {
  "specie" : "aeiou",
  "eggs" : 1.3579000000000001069366817318950779736042022705078125,
  "hatches" : 1.3579000000000001069366817318950779736042022705078125,
  "legDiameter" : 1.3579000000000001069366817318950779736042022705078125,
  "id" : 123456789,
  "place" : "aeiou",
  "wingSize" : 1.3579000000000001069366817318950779736042022705078125
} ];

exports.birdsGet = function(args, res, next) {
  /**
   * parameters expected in the args:
   

var examples = {};
  
  examples['application/json'] = [ {
  "specie" : "aeiou",
  "eggs" : 1.3579000000000001069366817318950779736042022705078125,
  "hatches" : 1.3579000000000001069366817318950779736042022705078125,
  "legDiameter" : 1.3579000000000001069366817318950779736042022705078125,
  "id" : 123456789,
  "place" : "aeiou",
  "wingSize" : 1.3579000000000001069366817318950779736042022705078125
} ];
  **/

  
  if(Object.keys(examples).length > 0) {
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify(examples[Object.keys(examples)[0]] || {}, null, 2));
  }
  else {
    res.end();
  }
  
  
}
exports.birdsPut = function(args, res, next) {
  /**
   * parameters expected in the args:
   * bird (Bird)
   **/

var examples = {};
  

  
  res.end();
}
exports.birdsPost = function(args, res, next) {
  /**
   * parameters expected in the args:
   * bird (Bird)
   **/

  examples['application/json'].push(args.bird.value);
  

  
  res.end();
}
exports.birdsDelete = function(args, res, next) {
  /**
   * parameters expected in the args:
   * bird (Bird)
   **/

var examples = {};
  

  
  res.end();
}
exports.birdsIdGet = function(args, res, next) {
  /**
   * parameters expected in the args:
   * id (BigDecimal)
   **/

var examples = {};
  
  examples['application/json'] = {
  "specie" : "aeiou",
  "eggs" : 1.3579000000000001069366817318950779736042022705078125,
  "hatches" : 1.3579000000000001069366817318950779736042022705078125,
  "legDiameter" : 1.3579000000000001069366817318950779736042022705078125,
  "id" : 123456789,
  "place" : "aeiou",
  "wingSize" : 1.3579000000000001069366817318950779736042022705078125
};
  

  
  if(Object.keys(examples).length > 0) {
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify(examples[Object.keys(examples)[0]] || {}, null, 2));
  }
  else {
    res.end();
  }
  
  
}
