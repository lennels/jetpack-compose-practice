## Day 1: February 3, 2026
**Focus:** Kotlin Data Foundations & Jetpack Compose State Management

### ✅ Tasks Completed
- [x] **Kotlin Modeling:** Mastered Data Classes and the `.copy()` function for immutability.
- [x] **Collection Pipelines:** Chained `.filter`, `.map`, and `.forEach` to process user lists.
- [x] **Compose UI:** Implemented `TextField` (Textbox) and `Switch` components.
- [x] **Architecture:** Applied **State Hoisting** to create stateless composables.
- [x] **Logic:** Used `when` expressions within function parameters for dynamic UI rendering.

### 🧠 Key Learnings
- **Immutability (Lead Mindset):** Using `.copy()` is safer for financial apps like Google Wallet. It ensures we don't mutate data in place, preventing "race conditions" where the UI shows one value while the logic holds another.
- **Data Flow:** Learned that in a pipeline, the type can change (e.g., mapping a `User` object to a `Double` balance). You must track these type changes to avoid calling properties that no longer exist.
- **Unidirectional Data Flow (UDF):** State flows **down** (from parent to child), and events flow **up** (callbacks). This is the core of **State Hoisting**.
- **Statelessness:** Making a Composable "stateless" (by passing in the value and a callback) makes it 100% reusable and easier to test.

### 🛠 Challenges / Bug Squash
- **The Type Trap:** Attempted to access `.balance` on a list that had already been mapped to `Double`. 
    * *Fix:* Realized the `map` transformation changed the stream content.
- **Recomposition Loop:** Initially struggled with where to put `mutableStateOf`. 
    * *Fix:* Hoisted the state to the caller to ensure the UI stays in sync with the source of truth.

### 📈 Tomorrow's Target
- [ ] Deep dive into **Null Safety**: Safe Calls (`?.`) and the Elvis Operator (`?:`).
- [ ] Connect Null Safety logic to Compose UI (e.g., showing a "N/A" string if a value is null).
- [ ] Continue with `Android Basics with Compose More Kotlin fundamentals`.