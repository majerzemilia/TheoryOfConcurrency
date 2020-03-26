function printAsync(s, cb) {
   var delay = Math.floor((Math.random()*1000)+500);
   setTimeout(function() {
       console.log(s);
       if (cb) cb();
   }, delay);
}

function task1(n, cb) {
    if (n>0) printAsync("1", function() {
        task2(n, cb);
    });
}

function task2(n, cb) {
    printAsync("2", function() {
        task3(n, cb);
    });
}

function task3(n, cb) {
    if(n==1) printAsync("3", cb);
    else printAsync("3", function() {
        task1(n-1, cb);
    });

}

function loop(n){
    task1(n, function() {
       console.log('done!');
    });
}

loop(4);


