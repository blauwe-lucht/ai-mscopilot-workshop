# AI Workshop — Exercises

## Exercise 1: A better interface for web search

**Goal:** compare a Google search against asking Copilot directly.

1. Google: `git rebase vs merge`. Skim the first 3 non-ad results.
2. Ask Copilot Chat: "What's the difference between `git rebase` and
   `git merge`, and when should I use each one?"
3. Compare: speed to a trustworthy answer, and whether it covers *when* to
   use each, not just *what* they do.
4. Follow-up: ask it to explain or expand part of the answer.

---

## Exercise 2: Reading something you can't read

**Goal:** show Copilot understanding something no search engine can help with:
an unfamiliar esoteric text.

1. Open `examples/wtf.txt`.
2. Paste it into Copilot Chat and ask: "What does this code do?"
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

1. Think of the ingredients still left in your fridge and ask Copilot Chat
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
2. Ask Copilot Chat to draft an outline or slide-by-slide structure from
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
2. Paste the ticket and the log file into Copilot Chat and ask it to
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
