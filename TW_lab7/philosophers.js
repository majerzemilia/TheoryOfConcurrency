
var Fork = function() {
    this.state = 0;
    return this;
}

Fork.prototype.acquire = function(count, comm, id, maxTry, delay, cb) {
		var Fork = this;
		if(Fork.state == 0){
			if(comm == 'second'){
				console.log(philosophers[id].waiting_time);
				philosophers[id].waiting_time = 0;
			}
			Fork.state = 1;
			console.log('acquired fork in cycle no by philosopher no ',count, comm, id);
			cb();
		}
		else{
			if(maxTry > 0){
				philosophers[id].waiting_time += delay;
				console.log('trying to acquire in cycle no by philosopher no, in probe no', count, comm, id, 10 - maxTry);
				setTimeout(function(){
					Fork.acquire(count, comm, id, --maxTry, delay*2, cb);
				}, delay*2);
			}
			else{
				console.log('\ntoo many probes\n');
				cb();
			}
		}
		// zaimplementuj funkcje acquire, tak by korzystala z algorytmu BEB
		// (http://pl.wikipedia.org/wiki/Binary_Exponential_Backoff), tzn:
		// 1. przed pierwsza proba podniesienia widelca Filozof odczekuje 1ms
		// 2. gdy proba jest nieudana, zwieksza czas oczekiwania dwukrotnie
		//    i ponawia probe itd.
}

Fork.prototype.release = function() { 
    this.state = 0; 
}

var Waiter = function() {
    this.state = N-1;
    return this;
}

Waiter.prototype.acquire = function(maxTry, delay, id, cb){
	var waiter = this;
	if(waiter.state > 0){
		waiter.state--;
		console.log('waiter allowed acquiring for philosopher no', id);
		cb();
	}
	else{
		if(maxTry > 0){
			philosophers[id].waiting_time += delay;
			console.log('trying to acquire waiter by philosopher no', id);
			setTimeout(function(){
				waiter.acquire(--maxTry, delay*2, id, cb);
			}, delay*2);
		}else{
			console.log('waiter didnt let the philosopher acquire fork :((', id);
		}
	}	
}
Waiter.prototype.release = function(){
	this.state++;
}

var Philosopher = function(id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id+1) % forks.length;
	this.waiting_time = 0;
    return this;
}

Philosopher.prototype.startNaive = function(count) {
	var philosopher = this;
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;
	setTimeout(function(){
		forks[f2].acquire(count, "first", id, 10, 1, 
		function(){
			setTimeout(function(){
				forks[f1].acquire(count, "second", id, 10, 1, 
				function(){
					console.log('philosopher no releases forks', id);
					forks[f2].release();
					forks[f1].release();
					count--;
					if(count>0) philosopher.startNaive(count);
					});
			}, 1);
		});
	}, 1);
		
	
    // zaimplementuj rozwiazanie naiwne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow
}

Philosopher.prototype.startAsym = function(count) {
	var philosopher = this;
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;
	var first, second;
	first = (id % 2 == 0) ? f2 : f1;
	second = (id % 2 == 0) ? f1 : f2;
	setTimeout(function(){
		forks[first].acquire(count, "first", id, 10, 1, 
		function(){
			setTimeout(function(){
				forks[second].acquire(count, "second", id, 10, 1, 
				function(){
					//console.log('philosopher no releases forks', id);
					forks[f2].release();
					forks[f1].release();
					count--;
					if(count>0) philosopher.startAsym(count);
					});
			}, 1);
		});
	}, 1);
    
    // zaimplementuj rozwiazanie asymetryczne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow
}

Philosopher.prototype.startConductor = function(count) {
    var philosopher = this;
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;
	setTimeout(function(){
		waiter.acquire(10, 1, id, function(){
			setTimeout(function(){
				forks[f2].acquire(count, "first", id, 10, 1, 
				function(){
					setTimeout(function(){
						forks[f1].acquire(count, "second", id, 10, 1, 
						function(){
							//console.log('philosopher no releases forks', id);
							forks[f2].release();
							forks[f1].release();
							count--;
							waiter.release();
							if(count>0) philosopher.startConductor(count);
							});
					}, 1);
				});
			}, 1);
		});
	}, 1);
		
    // zaimplementuj rozwiazanie z kelnerem
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow
}


var N = 5;
var waiter = new Waiter();
var forks = [];
var philosophers = [];
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (var i = 0; i < N; i++) {
    philosophers[i].startAsym(10);
}