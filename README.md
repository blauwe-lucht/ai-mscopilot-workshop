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

## Exercise 2: A question that needs checking

**Goal:** ask a technical question where the answer depends on versions and
on what Copilot knows, and practise checking its claims.

1. Ask Copilot: "When migrating from Java 17 to Java 21, what are the
   greatest challenges?"
2. Follow-up: add your real context, e.g. "We use Spring Boot 3.1, Maven,
   Lombok and Mockito, and run in Docker." Does the answer get more specific,
   or just longer?
3. Follow-up: "Which of these are actually new between Java 17 and 21, and
   which would apply to any Java upgrade?"
4. Verify: ask Copilot for its sources. Do the links exist, and do they say
   what Copilot claims? Check two claims against the official list of
   changes for Java 21: <https://openjdk.org/projects/jdk/21/>
5. Compare: search Google for `java 17 to 21 migration` and skim the first
   vendor blog. Which gave you a more trustworthy answer, and which a more
   useful one?

---

## Exercise 3: Reading something you can't read

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

## Exercise 4: You don't need English

**Goal:** show that your native language works just as well as English.

1. Pick a question from exercise 1, 2 or 3 and ask it in your native
   language instead.
2. Compare: is the answer as correct and fluent as the English one? Are code
   comments/variable names still in English, or do they follow your prompt?
3. Follow-up: ask a question the way you'd actually phrase it at work —
   mixing your native language with English technical terms.

---

## Exercise 5: A replacement for that app you downloaded once

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

## Exercise 6: Skipping the blank sheet

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

## Exercise 7: Making sense of a wall of logs

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

## Exercise 8: From a photo to a spreadsheet

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

---

## Exercise 9: Getting a password out of Git history

**Goal:** let Copilot guide you, step by step, through a difficult task you
rarely do: removing a password that has already been pushed.

1. Run `examples/git-secret-setup.sh` (on Windows: from Git Bash). It creates
   an `ai-workshop-repos` directory next to this repo, with a remote
   `origin`, your clone `webshop` and a colleague's clone `colleague`
   (leave that one alone for now). Three commits ago the database
   password was committed in `application.properties` and pushed. The
   latest commit replaced it with an environment variable, but the password
   is still in the history.
   Run the script again whenever you want to start over.
2. In `ai-workshop-repos/webshop`, look for yourself:
   `git log -p -- application.properties`.
3. Ask Copilot: "A database password was committed to our Git repo a few
   commits ago, and it has already been pushed. A later commit removed it
   again. How do I get it out of the history completely?"
4. Follow the steps one at a time. Paste the output of every command back
   into Copilot, errors included, and let it decide the next step.
5. Follow-up: when Copilot tells you to push with `--force`, ask: "What
   happens if a teammate pushed in the meantime? Is there a safer way?"
6. Verify: in `ai-workshop-repos`, make a fresh clone of `origin` and
   search it for the password. `git clone origin check`, then in directory
   check `git log -p --all -S Welkom123` must show nothing.
7. Follow-up: `ai-workshop-repos/colleague` is a teammate's clone. It still
   has the old history, plus one commit that isn't pushed yet. Try
   `git pull` there, paste the result into Copilot and ask what your
   colleague should do. Verify: their commit is still there, on top of your
   clean history, and `git log --all -S Welkom123` shows nothing.
8. Compare: did Copilot tell you to change the password anyway, since it
   has already leaked? Did it use a plain `--force`, or the safer
   `--force-with-lease` without being asked? Did it warn you about
   teammates who still have the old history? For your colleague, did it
   suggest something that throws away their commit (`git reset --hard`), or
   brings the password back (a merge)? Did it suggest
   `git filter-repo`, or the outdated `git filter-branch`? Did it ask about
   your situation, or dump all the steps at once?

---

## Exercise 10: Test cases before there is code

**Goal:** use Copilot to decide which tests to write while the feature is
still on paper, and to find the gaps in the requirements.

1. Open `examples/delivery-date.md` and read the user story. Write down the
   first five test cases you would want.
2. Paste the user story into Copilot and ask: "Which unit tests should be
   implemented for this during development? Give them as a table with the
   input and the expected result."
3. Follow-up: "Which boundary values and combinations are missing?"
4. Follow-up: "What is unclear or contradictory in these requirements?
   Which questions should we ask the product owner?"
5. Follow-up: "Which of these tests are unit tests, and which belong in an
   integration or end-to-end test?"
6. Compare: how many of your five test cases did Copilot find? Which of its
   test cases would you not have thought of? Did it invent requirements
   that aren't in the story, and present them as fact?

---

## Exercise 11: Code written by a genius

**Goal:** have Copilot explain code that is correct, but written by someone
who never considered that other people less smart would have to read it.

1. Open `examples/Checks.java`. Give yourself two minutes: what do the two
   `ok` methods check?
2. Paste the file into Copilot and ask:
   > The developer who wrote this is brilliant. Unfortunately, he left last
   > month. There are no tests since he is brilliant, nobody dares to touch
   > it, and now it needs a change. What does it do?
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

## Exercise 12: Tests for code nobody tested

**Goal:** have Copilot write unit tests for existing code, and find out
whether it catches bugs or just confirms what the code already does.

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
6. Compare: did Copilot test the boundaries and combinations, or only the
   easy cases? When a test failed, did it blame the code, or suggest
   changing the test to match the code?

---

## Exercise 13: Reviewing and refactoring code that looks fine

**Goal:** use Copilot to spot design problems in code that works and looks
tidy, and refactor it safely.

1. Open `examples/invoicing` in IntelliJ and read `InvoiceService.java`, with
   `orders.csv` as example input. Short methods, a record, streams,
   constants: what would you change?
2. Paste `InvoiceService.java` into Copilot and ask: "Review this class for
   design problems. Don't change anything yet."
3. Ask: "What about mixing abstraction levels?"
4. Before refactoring, ask for tests that pin down the current behaviour:
   "Write JUnit 5 tests for the current behaviour, using this orders.csv."
   (Paste `orders.csv` as well.) Run them; they must pass *before* you
   change anything.
5. Ask Copilot to refactor, one step at a time. For example: "Split this
   into classes with a single responsibility," then "Make the pricing
   rules testable without writing files." Run the tests after every step.
6. Follow-up: "Which of your changes alter behaviour, even slightly?"
7. Compare: did Copilot find the same problems as you? Did it make big
   changes in one go, or did it take small, safe steps? Did the tests stay
   green, or did it quietly change the tests to make them pass?

---

## Exercise 14: It compiles, but it doesn't run

**Goal:** let Copilot guide you through a dependency conflict: the code
compiles fine, but crashes at runtime with an error that doesn't point to
the cause.

1. Open `examples/export` in IntelliJ (open the `pom.xml` as a project). It
   exports orders as JSON and as CSV. Run `OrderExportTest` (`mvn clean test`):
   both tests fail with a `NoClassDefFoundError`.
2. Paste the error and the stack trace into Copilot and ask: "The code
   compiles fine, but the tests fail with this error. What's going on, and
   how do I fix it?"
3. Follow the steps one at a time. When Copilot asks for more information,
   such as the `pom.xml` or the output of `mvn dependency:tree`, give it.
4. Follow-up: "Why did Maven pick this version, and not the other one?"
5. Verify: both tests pass.
6. Compare: did Copilot find the real cause, or suggest adding the "missing"
   class some other way? Did it give you more than one fix (upgrading, a
   Jackson BOM, an exclusion, declaring the dependency yourself) and explain
   which one is best? Did it suggest versions that don't exist? How long
   would you have searched for `JacksonFeature` yourself?
