#!/bin/bash
# Exercise 5: Cleanup and push back to remote GitLab repository

cd GitDemo

# ─── STEP 1: Verify master is in clean state ───────────────────────────────
git status
# Expected: On branch master, nothing to commit, working tree clean
# If not clean, commit or stash pending changes first:
#   git add . && git commit -m "Cleanup before push"

# ─── STEP 2: List all available branches ───────────────────────────────────
git branch -a
# Local branches (should only have master after Exercise 4 cleanup)
# Remote branches: remotes/origin/master

# ─── STEP 3: Pull remote repository to sync master ─────────────────────────
git pull origin master
# Fetches remote changes and merges them into local master
# If remote has no new changes: Already up to date.
# If remote has new changes: Merge commit is created automatically

# ─── STEP 4: Push all pending commits from Exercises 3 and 4 ──────────────
git push origin master
# Pushes:
#   - Merge of GitNewBranch (Exercise 3)
#   - Conflict resolution commit (Exercise 4)
#   - .gitignore updates
# Output:
#   Enumerating objects: X, done.
#   Counting objects: X, done.
#   Writing objects: 100% (X/X), X bytes | X MiB/s, done.
#   To https://gitlab.com/<username>/GitDemo.git
#      <old-hash>..<new-hash>  master -> master

# ─── STEP 5: Verify changes reflected in remote repository ─────────────────
# Check remote tracking status
git log --oneline --graph --decorate --all
# origin/master should now point to same commit as local master:
# * <hash> (HEAD -> master, origin/master) Add *.orig to .gitignore
# *   <hash> Resolve merge conflict between master and GitWork in hello.xml
# |\
# | * <hash> Add hello.xml with branch-specific greeting
# * | <hash> Add hello.xml with master-specific greeting
# |/
# * <hash> (origin/GitNewBranch merged) Add feature files to GitNewBranch
# * <hash> Add welcome.txt - initial commit

# Verify remote is in sync
git status
# On branch master
# Your branch is up to date with 'origin/master'.
# nothing to commit, working tree clean

# ─── Open GitLab in browser to visually confirm ────────────────────────────
# Navigate to: https://gitlab.com/<username>/GitDemo
# Should see:
#   - welcome.txt
#   - feature-a.txt, feature-b.txt, branch-config.txt (from Exercise 3)
#   - hello.xml (resolved version from Exercise 4)
#   - .gitignore (with *.log and *.orig rules)
