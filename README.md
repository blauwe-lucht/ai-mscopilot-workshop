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
