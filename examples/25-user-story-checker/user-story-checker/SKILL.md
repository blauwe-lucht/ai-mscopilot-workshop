---
name: user-story-checker
description: >
  Checks whether a user story follows the format "As a <role>, I want
  <goal>, so that <value>" (in Dutch: "Als <rol> wil ik <doel>, zodat
  <waarde>"). Use when the user shares a user story, or asks to check,
  review or improve one.
---

# User Story Checker

Check one user story at a time against the rules below, and report the
result in the output format at the end.

## Rules

1. **Role**: the story starts with "As a <role>" ("Als <rol>"). The role is
   a person who uses the product, such as a customer or a support agent.
   "The system", "the database" or "a user" are not specific enough.
2. **Goal**: "I want <goal>" ("wil ik <doel>") describes what the role
   wants to do or have, not how to build it. Technologies, tables, buttons
   and frameworks don't belong here.
3. **Value**: "so that <value>" ("zodat <waarde>") explains why the role
   wants it. The value must add something new; repeating the goal in other
   words doesn't count.
4. **One goal**: the story has a single goal. Several goals joined by "and"
   or listed one after another should be separate stories.

## How to Check

- Check every rule, even when an earlier rule already fails.
- Quote the part of the story each verdict is about.
- Don't guess a missing role or value. Ask the user instead.
- Answer in the language of the user story.

## Output Format

Start with the heading **User story check** and a table:

| Rule | Found in the story | Verdict | Why |
|------|--------------------|---------|-----|
| Role | ... | OK / Problem | ... |
| Goal | ... | OK / Problem | ... |
| Value | ... | OK / Problem | ... |
| One goal | ... | OK / Problem | ... |

Then give one improved version of the story when at least one rule has a
problem, and list the questions for the product owner that the improved
version can't answer.
