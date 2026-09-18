# AI Workshop — Exercises

Exercises and examples for the Copilot demo portion of the workshop. Each
exercise gets its own section below for now; once there's enough content
we'll decide how to split things into separate files/folders.

---

## Exercise 1: A Better Interface to Google

**Goal:** compare a Google search against asking Copilot directly.

1. Google: `git rebase vs merge`. Skim the first 3 non-ad results.
2. Ask Copilot Chat: "What's the difference between `git rebase` and
   `git merge`, and when should I use each one?"
3. Compare: speed to a trustworthy answer, and whether it covers *when* to
   use each, not just *what* they do.
4. Follow-up: ask it to explain or expand part of the answer.

---

## Exercise 2: Reading Something You Can't Read

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
