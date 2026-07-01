#!/bin/bash
# Exercise 2: .gitignore — ignore .log files and log folders

# ─── SETUP: Navigate to existing GitDemo repo ──────────────────────────────
cd GitDemo

# ─── STEP 1: Create files that should be ignored ───────────────────────────
# Create a .log file
echo "This is a log file" > app.log
echo "Error details" > error.log

# Create a log folder with files inside
mkdir logs
echo "Server log content" > logs/server.log
echo "Debug log content" > logs/debug.log

# Check status — Git sees all these new files
git status
# Output: Untracked files: app.log, error.log, logs/

# ─── STEP 2: Create .gitignore file ────────────────────────────────────────
# Create .gitignore and add rules to ignore .log files and log folders
cat > .gitignore << 'EOF'
# Ignore all .log files
*.log

# Ignore the logs directory
logs/

# Ignore any folder named log or logs anywhere
**/logs/
**/log/
EOF

# ─── STEP 3: Verify .gitignore is working ──────────────────────────────────
git status
# Expected output:
#   Untracked files:
#     .gitignore
# app.log, error.log, and logs/ should NOT appear — they are ignored

# ─── STEP 4: Stage and commit .gitignore ───────────────────────────────────
git add .gitignore
git commit -m "Add .gitignore to exclude .log files and log folders"

# ─── STEP 5: Verify final status ───────────────────────────────────────────
git status
# Should show: nothing to commit, working tree clean
# Confirmed: .log files and logs/ folder are ignored by Git

# ─── BONUS: Check if a specific file is ignored ────────────────────────────
git check-ignore -v app.log
# Output: .gitignore:2:*.log    app.log
# Confirms the rule that matched

git check-ignore -v logs/server.log
# Output: .gitignore:5:logs/    logs/server.log

# ─── BONUS: See all ignored files ──────────────────────────────────────────
git status --ignored
# Shows both tracked files and ignored files

# ─── PUSH to remote ────────────────────────────────────────────────────────
git push origin master
