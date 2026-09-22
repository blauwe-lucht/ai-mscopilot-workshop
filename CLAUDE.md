# AI Workshop

## Purpose

This repo supports an AI workshop for developers and testers at a company
where **Microsoft Copilot** is the only AI assistant allowed for use (not
GitHub Copilot — they are different products). The
workshop has two parts:

1. **Lingo/theory** — explain the terminology and underlying techniques
   people encounter when entering the AI-assistant world (e.g. LLM, prompt,
   context window, tokens, RAG, fine-tuning, agents, MCP, hallucination,
   temperature, embeddings, system prompt vs. user prompt, local vs. online/
   cloud models, agentic AI).
2. **Live Copilot demo** — show what an online AI assistant can actually
   do, using this repo's exercise/example files as the material for
   demonstration and hands-on exercises.

Audience: mixed developers and testers. Format: half-day session (~3-4
hours).

## What lives in this repo

- **Only** exercise/example files used for the live demo and hands-on
  exercises with Copilot, plus any minimal supporting material needed to run
  them (e.g. setup notes for a given exercise).
- The lingo/glossary content lives in Obsidian, not here — do not create
  glossary/terminology docs in this repo.

## Exercises

All exercise content lives in the top-level `README.md`, one section per
exercise, until there's enough content to warrant splitting into separate
files/folders — don't split preemptively.

## Working conventions

- Keep this file up to date as the workshop content and repo structure
  evolve — treat it as the source of truth for what the workshop covers and
  how the repo is organized.
- Exercise files should be realistic but small enough to demo/explain within
  a workshop session.
- Since Microsoft Copilot is the only AI tool attendees are allowed to use
  at work, demo content and instructions should be specific to Microsoft
  Copilot (not generic "any AI assistant" instructions), even though this
  repo itself is being built with Claude Code.
- Microsoft Copilot has no IDE integration (no inline completions, no agent
  mode, no access to the project). Exercises must work through the chat
  (browser, Edge sidebar or app): paste or attach code/files, and copy
  results back into the IDE by hand.
