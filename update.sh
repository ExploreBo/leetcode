total=$(cat README.md | grep "(https" | wc -l)
total=`echo $total | sed 's/ *$//g'`
sed -i'.bak'  "1 s/[0-9].*/${total})/" README.md
