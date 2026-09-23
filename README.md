# AI Workshop — Exercises

## Exercise 1: A better interface for web search

**Goal:** compare a Google search against asking Copilot directly.

1. Google: `git rebase vs merge`. Skim the first 3 non-ad results.
2. Ask Copilot: "What's the difference between `git rebase` and
   `git merge`, and when should I use each one?"
3. Compare: speed to a trustworthy answer, and whether it covers *when* to
   use each, not just *what* they do.
4. Follow-up: ask it to explain or expand part of the answer.

---

## Exercise 2: Reading something you can't read

**Goal:** show Copilot understanding something no search engine can help with:
an unfamiliar esoteric text.

1. Open `examples/wtf.txt`.
2. Paste it into Copilot and ask: "What does this code do?"
3. Compare: a Google search for a random snippet like this returns nothing
   useful — there's no textual match to find. Copilot instead reasons about
   the code itself.
4. Follow-up: if available, ask ChatGPT and Claude the same question.
5. Verify: find a site that can run the code and see if the assistants were
   right.

---

## Exercise 3: You don't need English

**Goal:** show that your native language works just as well as English.

1. Pick a question from exercise 1 or 2 and ask it in your native language
   instead.
2. Compare: is the answer as correct and fluent as the English one? Are code
   comments/variable names still in English, or do they follow your prompt?
3. Follow-up: ask a question the way you'd actually phrase it at work —
   mixing your native language with English technical terms.

---

## Exercise 4: A replacement for that app you downloaded once

**Goal:** show that a chat assistant can replace single-purpose apps/sites
for everyday tasks, not just coding questions.

1. Think of the ingredients still left in your fridge and ask Copilot
   what meal you could make with them.
2. Follow-up: add a constraint: vegetarian, under 20 minutes, no oven,
   kid-friendly, etc.
3. Follow-up: ask for a website that can do this.
4. Verify: check if the website is indeed able to do the same and if using
   it is as easy as asking an AI-assistant.
5. Compare: how many separate apps/sites/tabs would this have taken
   otherwise (recipe search, a filter for dietary needs, maybe a
   converter for quantities)?

---

## Exercise 5: Skipping the blank sheet

**Goal:** use AI to get past the blank-page phase when creating something
from scratch, for example a presentation from a set of requirements.

1. Write down 3-4 requirements for a short presentation (audience, topic,
   number of slides, tone/purpose — e.g. "10-minute update for management on
   our test automation coverage"). If inspiration is failing, use this:

   > I want to give a presentation on using AI. The presentation is about
   > one hour. The audience is broad, both techies and normies. Experience
   > level is from none to creating skills. Any ideas how to take this on?
2. Ask Copilot to draft an outline or slide-by-slide structure from
   those requirements.
3. Follow-up: ask it to flesh out one slide's content, or suggest a stronger
   opening/closing.
4. Compare: how much of the
   "staring at an empty slide" phase did this skip? What would you still
   change yourself?

---

## Exercise 6: Making sense of a wall of logs

**Goal:** show Copilot analyzing noisy, real-world logs faster
than scanning them by hand.

> A customer says their order failed at checkout with some kind of server
> error. They didn't note the exact time. Can you find out what happened?

1. `examples/order-service.log` is 2,500 lines of logs.
   Try skimming for a minute — would you have found
   it from the ticket alone?
2. Paste the ticket and the log file into Copilot and ask it to
   investigate.
3. Follow-up: "Is this a one-off, or a problem other customers will hit
   too?"
4. Compare: did Copilot locate the actual failure and reason about the
   pattern building up to it, or
   did it grab the first ERROR-looking line and stop? How long would
   finding it yourself have taken, starting only from the ticket?

> Note: Copilot is severely limited in the amount of logging it can
> handle. Files larger than 10.000 lines can't be retrieved by the
> assistant ('file has expired') and only the first +- 3.000 lines
> are seen ('file truncated'). ChatGPT and Claude work correctly with
> a 12.000 line file, probably much larger.

---

## Exercise 7: Code written by a genius

**Goal:** have Copilot explain code that is correct, but written by someone
who never considered that other people less smart would have to read it.

> The developer who wrote this is brilliant. Unfortunately, he left last
> month. There are no tests since he is brilliant, nobody dares to touch
> it, and now it needs a change. What does it do?

1. Open `examples/Checks.java`. Give yourself two minutes: what do the two
   `ok` methods check?
2. Paste the file into Copilot and ask: "Explain what this code does, step
   by step, for someone who doesn't know Java streams."
3. Follow-up: "Why `c > 57`, `- 55` and `% 97`? Where do these numbers come
   from?"
4. Follow-up: "How brilliant was he really?"
5. Follow-up: "Rewrite this so a junior developer can maintain it. Use clear
   names and no magic numbers."
6. Verify: does the rewrite still give the same results? Ask Copilot for a
   few valid and invalid inputs and try them on both versions.
7. Compare: did Copilot recognize *what* the code is for, or only describe
   the mechanics line by line? Would you trust its rewrite without tests?
   (See the next exercise.)

---

## Exercise 8: Tests for code nobody tested

**Goal:** have Copilot write unit tests for existing code, and find out
whether it catches bugs or just confirms what the code already does.

> A colleague wrote this discount calculator before going on leave. It goes
> to production on Friday and has no tests. Can you cover it?

1. Open `examples/pricing` in IntelliJ (open the `pom.xml` as a project).
   Read `PriceCalculator.java` for a minute. Do you see anything wrong?
2. Paste `PriceCalculator.java` into Copilot and ask: "Write JUnit 5 tests
   for this class. Base them on the business rules in the comment."
3. Copy the tests into `src/test/java/shop/PriceCalculatorTest.java` and run
   them.
4. Follow-up: for every failing test, paste the failure into Copilot and ask:
   "Is the test wrong or the code?"
5. Follow-up: check the quality of the tests. Improve it by giving a new
   prompt.
6. Tester variant: paste **only** the business rules from the comment, no
   code, and ask for test cases (boundary values, combinations). Compare
   them with the test cases you would have written.
7. Compare: did Copilot test the boundaries and combinations, or only the
   easy cases? When a test failed, did it blame the code, or suggest
   changing the test to match the code?

---

## Exercise 9: From a photo to a spreadsheet

**Goal:** show Copilot reading a photo of a printed page and turning it into
structured data you can use right away.

1. Open a new chat in Copilot and drag `examples/spanish.jpg` into it. It's a
   phone photo of a vocabulary list from a Spanish textbook.
2. Ask: "From UNIDAD 3 create a csv, separated by ';'. First column name is
   'Spanish', second is 'Dutch'."
3. Save the result as a `.csv` file and open it in Excel. Does every row end
   up in the right column?
4. Verify: pick 10 random words and check them against the photo.
5. Follow-up: "Create a quiz from this list: give me 10 Dutch words and I'll
   answer in Spanish."
6. Compare: how long would typing this list yourself have taken? What would
   you normally use for this: a scanner app, OCR software, or just typing it
   over?
