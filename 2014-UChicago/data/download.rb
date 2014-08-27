s = %w(a b c d e f g h j k l)
s.each do |c|
    `wget http://icpc.cs.uchicago.edu/tryouts2014/pset/#{c}.in`
    `wget http://icpc.cs.uchicago.edu/tryouts2014/pset/#{c}.out`
end
