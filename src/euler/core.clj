(ns euler.core)

; problem 1
(reduce + (filter #(or (= 0 (mod % 5))
                       (= 0 (mod % 3)))
                  (take 1000 (iterate inc 0))))

; problem 2
(defn fib []
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))

(reduce + (filter even? (take-while #(< % 4000000) (fib))))

; problem 3
(defn prime? [n]
  (let [pred (fn [x] (= 0 (mod n x)))]
    (not (some pred (range 2 n)))))

(defn prime2? [n]
  (if (= 2 n) true
      (let [pred (fn [x] (= 0 (mod n x)))]
        (not (some pred (range 2 (+ 1 (Math/sqrt n))))))))

(defn recursive-factors [n primes-in-range factorlist]
  (let [first-prime (first primes-in-range)
        next-primes (rest primes-in-range)]
    (cond
     (> first-prime n) factorlist
     (= 0 (mod n first-prime)) (recur (/ n first-prime)
                                      primes-in-range
                                      (conj factorlist first-prime))
     :else (recur n next-primes factorlist))))

(defn prime-factors [n]
  (let [to-range n
        primes (filter prime2? (range 2 to-range))]
    (recursive-factors n primes [])))


; problem 4
(defn palindrom? [x]
  (= (apply str ( reverse x)) x))


(defn palindroms []
  (for [x (reverse (range 100 1000))
        y (reverse (range 100 1000))
        :when (and (>= x y) (palindrom? (str (* x y))))]
    [x y]))

(defn palindroms-result [all-palindroms]
  (apply max (map (fn [[x y]] (* x y)) all-palindroms)))

; problem 5
(defn smallest-multiple [divisors acc]
  (let [divider (first divisors)
        other (rest divisors)]
    (if divider 
      (recur
       (rest (map #(if (= 0 (mod % divider)) (/ % divider) %) divisors))
       (conj acc divider))
      acc)))

(apply * (smallest-multiple (range 2 21) []))

;problem 6

(let [sum-range (apply + (range 1 101))
      sum-square (apply + (map #(* % %) (range 1 101)))]
  (- (* sum-range sum-range) sum-square))


; problem 7
(take 1 (drop 10000 (for [x (iterate inc 2) :when (prime? x)] x )))

; problem 8
(def thousand-digit "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450")

(defn divide5 [x y]
  (if (> 5 (count x)) y
      (recur (rest x) (conj y (map #(Integer/valueOf (str %)) (take 5 x))))))

(apply max (map #(apply * %) (divide5 thousand-digit [])))

; problem 9
(for [a (range 1 1001)
      b (range 1 1001)
      c (range 1 1001)
      :when (and (< a b c)
                 (= (+ (* a a) (* b b)) (* c c))
                 (= 1000 (+ a b c)))]
  (* a b c))

; problem 10
(time (apply + (filter prime2? (range 2 2000000))))


; problem 12 -- not finished
(defn divisors [n]
  (filter #(= 0 (mod n %)) (range 1 (+ n 1))))

(defn sum-till [n]
  (apply + (range 1 (inc n))))

(time (take 1 (filter #(< 500 (count %)) (pmap divisors (pmap sum-till (iterate inc 1))))))


;problem 13
(def numbers [37107287533902102798797998220837590246510135740250N
              46376937677490009712648124896970078050417018260538N
              74324986199524741059474233309513058123726617309629N
              91942213363574161572522430563301811072406154908250N
              23067588207539346171171980310421047513778063246676N
              89261670696623633820136378418383684178734361726757N
              28112879812849979408065481931592621691275889832738N
              44274228917432520321923589422876796487670272189318N
              47451445736001306439091167216856844588711603153276N
              70386486105843025439939619828917593665686757934951N
              62176457141856560629502157223196586755079324193331N
              64906352462741904929101432445813822663347944758178N
              92575867718337217661963751590579239728245598838407N
              58203565325359399008402633568948830189458628227828N
              80181199384826282014278194139940567587151170094390N
              35398664372827112653829987240784473053190104293586N
              86515506006295864861532075273371959191420517255829N
              71693888707715466499115593487603532921714970056938N
              54370070576826684624621495650076471787294438377604N
              53282654108756828443191190634694037855217779295145N
              36123272525000296071075082563815656710885258350721N
              45876576172410976447339110607218265236877223636045N
              17423706905851860660448207621209813287860733969412N
              81142660418086830619328460811191061556940512689692N
              51934325451728388641918047049293215058642563049483N
              62467221648435076201727918039944693004732956340691N
              15732444386908125794514089057706229429197107928209N
              55037687525678773091862540744969844508330393682126N
              18336384825330154686196124348767681297534375946515N
              80386287592878490201521685554828717201219257766954N
              78182833757993103614740356856449095527097864797581N
              16726320100436897842553539920931837441497806860984N
              48403098129077791799088218795327364475675590848030N
              87086987551392711854517078544161852424320693150332N
              59959406895756536782107074926966537676326235447210N
              69793950679652694742597709739166693763042633987085N
              41052684708299085211399427365734116182760315001271N
              65378607361501080857009149939512557028198746004375N
              35829035317434717326932123578154982629742552737307N
              94953759765105305946966067683156574377167401875275N
              88902802571733229619176668713819931811048770190271N
              25267680276078003013678680992525463401061632866526N
              36270218540497705585629946580636237993140746255962N
              24074486908231174977792365466257246923322810917141N
              91430288197103288597806669760892938638285025333403N
              34413065578016127815921815005561868836468420090470N
              23053081172816430487623791969842487255036638784583N
              11487696932154902810424020138335124462181441773470N
              63783299490636259666498587618221225225512486764533N
              67720186971698544312419572409913959008952310058822N
              95548255300263520781532296796249481641953868218774N
              76085327132285723110424803456124867697064507995236N
              37774242535411291684276865538926205024910326572967N
              23701913275725675285653248258265463092207058596522N
              29798860272258331913126375147341994889534765745501N
              18495701454879288984856827726077713721403798879715N
              38298203783031473527721580348144513491373226651381N
              34829543829199918180278916522431027392251122869539N
              40957953066405232632538044100059654939159879593635N
              29746152185502371307642255121183693803580388584903N
              41698116222072977186158236678424689157993532961922N
              62467957194401269043877107275048102390895523597457N
              23189706772547915061505504953922979530901129967519N
              86188088225875314529584099251203829009407770775672N
              11306739708304724483816533873502340845647058077308N
              82959174767140363198008187129011875491310547126581N
              97623331044818386269515456334926366572897563400500N
              42846280183517070527831839425882145521227251250327N
              55121603546981200581762165212827652751691296897789N
              32238195734329339946437501907836945765883352399886N
              75506164965184775180738168837861091527357929701337N
              62177842752192623401942399639168044983993173312731N
              32924185707147349566916674687634660915035914677504N
              99518671430235219628894890102423325116913619626622N
              73267460800591547471830798392868535206946944540724N
              76841822524674417161514036427982273348055556214818N
              97142617910342598647204516893989422179826088076852N
              87783646182799346313767754307809363333018982642090N
              10848802521674670883215120185883543223812876952786N
              71329612474782464538636993009049310363619763878039N
              62184073572399794223406235393808339651327408011116N
              66627891981488087797941876876144230030984490851411N
              60661826293682836764744779239180335110989069790714N
              85786944089552990653640447425576083659976645795096N
              66024396409905389607120198219976047599490197230297N
              64913982680032973156037120041377903785566085089252N
              16730939319872750275468906903707539413042652315011N
              94809377245048795150954100921645863754710598436791N
              78639167021187492431995700641917969777599028300699N
              15368713711936614952811305876380278410754449733078N
              40789923115535562561142322423255033685442488917353N
              44889911501440648020369068063960672322193204149535N
              41503128880339536053299340368006977710650566631954N
              81234880673210146739058568557934581403627822703280N
              82616570773948327592232845941706525094512325230608N
              22918802058777319719839450180888072429661980811197N
              77158542502016545090413245809786882778948721859617N
              72107838435069186155435662884062257473692284509516N
              20849603980134001723930671666823555245252804609722N
              53503534226472524250874054075591789781264330331690N])

(apply str (take 10 (str (apply + numbers))))


; problem 25
(count (take-while
        #(> 1000 (count (str %)))
        (fib)))
