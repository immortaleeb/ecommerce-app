# AI rules

If you are an AI agent, keep in mind the following rules:

- Never write comments or javadoc, unless you are explicitly asked to
- When writing tests, use junit5 and assertj. Never use a mocking library, unless explicitly asked.
- Instead of using mockito, manually implement fake in-memory implementations and use those to set up and assert tests.
- Before making any changes, describe a high-level plan of what you are about to do and ask me to confirm before making any changes.
