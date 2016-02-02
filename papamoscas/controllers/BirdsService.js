'use strict';
var path = require('path');
var DataStore = require('nedb');
var dbFileName = path.join(__dirname, 'birds.json');
var db = new DataStore({
    filename: dbFileName,
    autoload: true
});

db.find({}, function(err, birds) {

    if (birds.length == 0) {
        console.log('Empty DB, loading initial data');

       var bird1 = {
            specie: 'Ficedula hypoleuca iberiae',
            eggs: '30',
            hatches: '12',
            legDiameter: '13.3',
            id: '1',
            place: 'Huelva',
            wingSize: '20.1'
        };

        db.insert([bird1]);

    } else {
        console.log('DB has ' + birds.length + ' birds ');
    }

});

exports.birdsGet = function(args, res, next) {

    console.log('New GET request');

    db.find({}, function(err, birds) {
            if (birds.length > 0) {
            res.setHeader('Content-Type', 'application/json');
            res.end(JSON.stringify(birds));
        } else {
            res.status(404);
			res.header("Content-Type", "application/json; charset=utf-8");
            res.end();
        }
    });
}
	
exports.birdsPut = function(args, res, next) {
    console.log('New PUT request');
    res.sendStatus(501);
    res.end();
}

/*
exports.birdsPost = function(args, res, next) {
    console.log('New POST request');
    console.log(args.bird.value));
db.insert(args);
res.end();
}
/*
exports.birdsDelete = function(args, res, next) {
    console.log('New DELETE request');
    res.sendStatus(501);
    res.end();
    res.end();
}

exports.birdsIdGet = function(args, res, next) {


    var id = args.id.value;
    console.log('New DELETE request for bird with id ' + id);

    db.remove({
        _id: id
    }, {}, function(err, numRemoved) {
        console.log("Birds removed: " + numRemoved);
        if (numRemoved == 1)
            res.sendStatus(200);
        else
            res.sendStatus(404);
    });


}*/
