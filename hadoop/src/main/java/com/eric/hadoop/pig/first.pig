records = Load '/home/cloudera/datasets/sample.txt' AS(year:chararray,temp:int,quality:int);
filterrecords= Filter records By temp!=9999 And (quality==0 OR quality==1 OR quality==4 OR quality==5 OR quality==9);
group_records= GROUP filterrecords BY year;
max_tmp= foreach group_records GENERATE group,MAX(filterrecords.year);