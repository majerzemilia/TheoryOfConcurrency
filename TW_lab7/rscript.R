setwd("C:/Users/majer/Desktop/Infa/SEM V/TW/TW_lab7")
asym5 = read.csv("asym5.csv", header=FALSE)
asym50 = read.csv("asym50.csv", header=FALSE)
asym100 = read.csv("asym100.csv", header=FALSE)
asym200 = read.csv("asym200.csv", header=FALSE)
asym300 = read.csv("asym300.csv", header=FALSE)
asym400 = read.csv("asym400.csv", header=FALSE)
asym500 = read.csv("asym500.csv", header=FALSE)
cond5 = read.csv("cond5.csv", header=FALSE)
cond50 = read.csv("cond50.csv", header=FALSE)
cond100 = read.csv("cond100.csv", header=FALSE)
cond200 = read.csv("cond200.csv", header=FALSE)
cond300 = read.csv("cond300.csv", header=FALSE)
cond400 = read.csv("cond400.csv", header=FALSE)
cond500 = read.csv("cond500.csv", header=FALSE)

avga5 = mean(asym5$V1)
avga50 = mean(asym50$V1)
avga100 = mean(asym100$V1)
avga200 = mean(asym200$V1)
avga300 = mean(asym300$V1)
avga400 = mean(asym400$V1)
avga500 = mean(asym500$V1)
avgc5 = mean(cond5$V1)
avgc50 = mean(cond50$V1)
avgc100 = mean(cond100$V1)
avgc200 = mean(cond200$V1)
avgc300 = mean(cond300$V1)
avgc400 = mean(cond400$V1)
avgc500 = mean(cond500$V1)

avg_asym = data.frame(
  x = c(5, 50, 100, 200, 300, 400, 500),
  y = c(avga5, avga50, avga100, avga200, avga300, avga400, avga500)
)
avg_asym$z = "asym"

avg_cond = data.frame(
  x = c(5, 50, 100, 200, 300, 400, 500),
  y = c(avgc5, avgc50, avgc100, avgc200, avgc300, avgc400, avgc500)
)
avg_cond$z = "cond"

avgs = rbind(avg_asym, avg_cond)

ggplot(avgs, aes(x = x, y = y, color=z))+ geom_point() + ylab("sredni czas oczekiwania") +
  xlab("Liczba filozofów");
       