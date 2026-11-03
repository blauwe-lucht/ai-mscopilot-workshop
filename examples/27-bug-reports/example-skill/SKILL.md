---
name: "jira-bug-report-writer"
description: >
  Converts informal customer reports, support tickets, emails, chat messages,
  and application logs into evidence-based bug reports that can be directly
  imported into Jira. Produces factual
  bug reports, quotes relevant log lines with timestamps, searches for similar
  failures in supplied logs, assigns priorities according to defined rules,
  and moves unsupported assumptions into an Open Questions section.
---

# Jira Bug Report Writer

## Purpose

Convert an informal problem report and supporting evidence into a bug report that can be directly imported into Jira.

The resulting bug report must be suitable for immediate import into Jira and must distinguish clearly between:

- Confirmed facts
- Evidence
- Open questions

The report must never present assumptions, hypotheses, or interpretations as facts.

---

# When to Use

Use this skill when:

- A user says "turn this into a bug report"
- A user provides a customer complaint and logs
- A user provides support tickets and diagnostic information
- A user wants a Jira-ready bug report
- A user wants investigation findings summarized into a bug

Do not use this skill for:

- Incident postmortems
- Root-cause analysis documents
- Feature requests
- User stories
- Change requests

---

# Core Principles

## Facts Only

Only include information that is explicitly present in:

1. The user's report
2. Attached logs
3. Attached files
4. Other supplied evidence

Do not invent:

- Root causes
- Business impact
- Affected customer counts
- Reproduction steps
- Severity explanations
- Workarounds
- User-facing error messages
- Technical explanations
- Payment issues
- Data loss
- Financial impact

If something is not directly supported by evidence, move it to **Open Questions**.

---

## Evidence Hierarchy

When evidence conflicts, use the following order:

1. Attached logs
2. Attached files
3. User-supplied report
4. Agent observations

Statements not supported by levels 1-3 must not be presented as facts.

---

## Separate Facts From Questions

Bad:

```text
The automatic retry may have caused a duplicate payment.
```

Good:

```text
Customer reported they believe they may have paid twice.
```

And:

```text
Open Questions:
- Was the customer charged once or multiple times?
```

---

## Quote Evidence

When logs support a finding:

- Quote exact log lines
- Include timestamps
- Preserve original wording
- Include only relevant entries

Bad:

```text
Large stack trace containing hundreds of lines.
```

Good:

```text
2026-09-14 17:34:45 ERROR order-service - Failed to process order 119196
```

---

## Minimize Noise

Prefer concise evidence.

Include:

- Warnings
- Errors
- Retries
- Success events directly related to the issue

Avoid:

- Large stack traces when the error message alone is sufficient
- Unrelated log entries
- Excess contextual noise

---

## Remove Metadata

Never include tool or platform metadata.

Remove items such as:

```text
cite...
<File>...
<Person>...
【1-72d8b2】
```

The final Jira ticket must contain only content suitabl for Jira.

---

# Priority Rules

Assign priority using confirmed evidence only.

## Highest

Use when evidence shows:

- Nobody can place orders
- Core functionality is unavailable for all users

Examples:

- Entire checkout system unavailable
- All API requests failing
- Complete outage

---

## High

Use when evidence shows:

- Some customers cannot place orders
- Customers may be charged incorrectly
- A business-critical function is failing for part of the user base

Examples:

- Checkout failures affecting some customers
- Failed order placement
- Incorrect billing reports

---

## Medium

Use when:

- A workaround exists
- Impact is limited
- Users can still complete their objective through an alternative path

---

## Low

Use when:

- The issue is cosmetic
- Functionality remains unaffected

Examples:

- Visual alignment issues
- Typographical errors
- Incorrect icons

---

## Priority Determination Procedure

1. Review the reported impact.
2. Review the evidence.
3. Apply the priority rules.
4. Select the lowest priority consistent with the available facts.
5. Do not escalate priority based on assumptions.

If evidence is insufficient:

```text
Open Questions:
- What is the customer impact?
```

Use priority `Medium` until triaged.

---

# Log Analysis Procedure

When logs are supplied, perform the following steps.

## Step 1

Locate the reported identifier:

- Order ID
- Request ID
- Transaction ID
- Correlation ID
- User ID
- Session ID

---

## Step 2

Collect related events:

- Receipt
- Warning
- Failure
- Retry
- Recovery
- Success

---

## Step 3

Quote Relevant Evidence

Include exact log lines with timestamps.

Example:

```text
2026-09-14 17:34:15 INFO order-service - Order 119196 received

2026-09-14 17:34:45 ERROR order-service - Failed to process order 119196

2026-09-14 17:34:55 INFO order-service - retrying order 119196

2026-09-14 17:34:56 INFO order-service - Order 119196 processed in 123ms
```

---

## Step 4

Search For Similar Failures

Search the entire supplied log for:

- Identical error messages
- Identical exceptions
- Identical warning patterns
- Similar retry behaviour

---

## Step 5

Report Findings Conservatively

Good:

```text
Only order 119196 in the supplied log exhibited this timeout pattern.
```

Bad:

```text
This appears to be an isolated incident.
```

The second statement is an interpretation.

---

# Open Questions Rules

Anything not directly supported by evidence belongs here.

Examples:

```text
Open Questions:
- Did the order complete successfully from a business perspective?
- Was the customer charged once or multiple times?
- What error was displayed to the customer?
- Is the retry behaviour expected?
- Why was the connection pool fully utilized?
```

---

# Jira Output Format

Produce a CSV file for Jira's CSV import: one header row and one row per
bug report.

Output only the CSV, in a single code block marked `csv`, so the user can
save it as a `.csv` file without editing it.

## Columns

Use exactly these columns, in this order:

```csv
"Summary","Issue Type","Priority","Description"
```

- **Summary**: short factual summary, one line.
- **Issue Type**: always `Bug`.
- **Priority**: exactly one of `Highest`, `High`, `Medium`, `Low`.
- **Description**: the rest of the report, in plain text, with these
  sections in this order: reported facts, affected identifiers, relevant
  log entries, open questions.

## CSV Rules

Jira rejects or mangles rows that break these rules:

- Separate fields with a comma.
- Wrap every field in double quotes, including the header.
- Write a double quote inside a field as two double quotes (`""`).
- Line breaks are allowed inside a quoted field; use them to separate the
  sections of the description.
- Don't add empty lines between rows or after the last row.
- Don't use Markdown in the fields: no `**`, no backticks, no tables.

## Example

```csv
"Summary","Issue Type","Priority","Description"
"Checkout error reported for order 119196","Bug","High","Reported facts:
A customer called the reporter. She received an error at checkout ""yesterday around half past 5"". According to the reporter, the order went through in the end. The customer believes she paid twice.

Affected identifiers:
- Order 119196

Relevant log entries:
2026-09-14 17:34:14 WARN  db.pool - connection pool usage: 20/20 checked out, requests queuing
2026-09-14 17:34:45 ERROR order-service - Failed to process order 119196
2026-09-14 17:34:55 INFO  order-service - retrying order 119196
2026-09-14 17:34:56 INFO  order-service - Order 119196 processed in 123ms

Open questions:
- Was the customer charged once or multiple times?
- What error was displayed to the customer?"
```

---

# Writing Rules

## Summary

Summaries must be factual.

Good:

```text
Checkout error reported for order 119196
```

Bad:

```text
Database connection pool exhaustion causes duplicate payments
```

The second summary introduces conclusions not supported by evidence.

---

## Description

Include:

- What was reported
- Who reported it (if known)
- Relevant identifiers
- Confirmed observations

Do not include:

- Suspected causes
- Conclusions
- Mitigation recommendations

unless explicitly requested.

---

# Final Validation Checklist

Before returning the Jira ticket, verify:

- [ ] Only confirmed facts are presented as facts
- [ ] Unsupported assumptions have been moved to Open Questions
- [ ] Relevant log entries are quoted exactly
- [ ] Log timestamps are included
- [ ] Similar failures have been searched for in the supplied logs
- [ ] Priority follows the defined rules
- [ ] No root cause is invented
- [ ] No business impact is invented
- [ ] No reproduction steps are invented
- [ ] No user-facing error messages are invented
- [ ] No platform metadata remains
- [ ] The output is a single CSV code block with the header
      `"Summary","Issue Type","Priority","Description"`
- [ ] Every field is quoted and inner double quotes are doubled
