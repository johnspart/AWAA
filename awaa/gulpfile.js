/**
 *  Welcome to your gulpfile!
 *  The gulp tasks are splitted in several files in the gulp directory
 *  because putting all here was really too long
 */

'use strict';

var gulp = require('gulp');
var wrench = require('wrench');
var gutil = require('gulp-util');
var connect = require('gulp-connect');
var open = require('gulp-open');


var config = {
  port: '9000',
  baseDevUrl: 'http://localhost',
  paths: {
    html: './src/*.html',
    dist: './dist'
  }

};


gulp.task('connect', function() {
  connect.server({
    root: ['dist'],
    port: config.port,
    base: config.baseDevUrl,
    livereload: true
  });
});

gulp.task('open', ['connect'], function() {
  gulp.src('dist/index.html')
    .pipe(open('', {
      url: config.baseDevUrl + ':' + config.port + '/',
      app: 'mozilla firefox'
    }));
});

gulp.paths = {
  src: 'src',
  dist: 'dist',
  tmp: '.tmp',
  e2e: 'e2e'
};
/**
 *  This will load all js or coffee files in the gulp directory
 *  in order to load all gulp tasks
 */
wrench.readdirSyncRecursive('./gulp').filter(function(file) {
  return (/\.(js|coffee)$/i).test(file);
}).map(function(file) {
  require('./gulp/' + file);
});

gulp.errorHandler = function(title) {
  return function(err) {
    gutil.log(gutil.colors.red('[' + title + ']'), err.toString());
    this.emit('end');
  };
};


/**
 *  Default task clean temporaries directories and launch the
 *  main optimization build task
 */
gulp.task('default', ['clean'], function() {
  gulp.start('build');
});
