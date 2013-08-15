(ns linewrap-kata.core-test
  (:use midje.sweet)
  (:use [linewrap-kata.core]))

(def ex-string "This one should be split.")
(def ex-split "This one\nshould be\nsplit.")
(def ex-breakpoint 15)

(facts "about break-string"
  (future-fact "Splits after words, smaller than breakpoint."
    (break-string "Hello world" 5) => "Hello\nworld")
  (fact "Empty splits stay the same."
    (break-string "" .unimportant.) => ""))

(facts "about combine-lines"
  (let [long-word "itlgenitgleiglebfiglebigelstilgseyvegslitegisssyflgezige"
        ex        ["This" "is" "a" "test" "of" "a" "sentence" "bla" "bla" "bla"
                   "bla" "foo" "bar."]
        ex-sol    ["This is a test" "of a sentence" "bla bla bla bla" "foo bar."]
        ]

    (fact "A line isn’t longer than breakpoint, except when there is a single word
          longer than breakpoint."
      (combine-lines ex ex-breakpoint) => ex-sol
      (combine-lines
        (conj ex long-word "baz" "boo")
        ex-breakpoint)
      => (conj ex-sol long-word "baz boo")

    (fact "If an empty string is given, it is returned directly."
      (combine-lines [""] .unimportant.) => [""]))))
