#!/bin/bash
# Exercise 3: Branching and Merging with P4Merge visual diff tool

# ─── SETUP ─────────────────────────────────────────────────────────────────
# Configure P4Merge as diff/merge tool (Windows):
git config --global diff.tool p4merge
git config --global difftool.p4merge.path "C:/Program Files/Perforce/p4merge.exe"
git config --global merge.tool p4merge
git config --global mergetool.p4merge.path "C:/Program Files/Perforce/p4merge.exe"

cd GitDemo

# ─── BRANCHING ─────────────────────────────────────────────────────────────

# STEP 1: Create a new branch 'GitNewBranch'
git branch GitNewBranch

# STEP 2: List all local and remote branches
# * marks the currently active branch
git branch -a
# Output:
#   GitNewBranch
# * master
#   remotes/origin/master

# STEP 3: Switch to the new branch
git checkout GitNewBranch
# OR modern syntax:
# git switch GitNewBranch

# Verify current branch
git branch
# * GitNewBranch
#   master

# STEP 4: Add files with content to the branch
echo "Feature A implementation" > feature-a.txt
echo "Feature B implementation" > feature-b.txt
echo "Branch specific config" > branch-config.txt

# STEP 5: Stage and commit changes to the branch
git add .
git commit -m "Add feature files to GitNewBranch"

# STEP 6: Verify status — working tree is clean
git status
# On branch GitNewBranch
# nothing to commit, working tree clean

# ─── MERGING ───────────────────────────────────────────────────────────────

# STEP 7: Switch back to master
git checkout master

# STEP 8: List differences between master and branch (CLI diff)
git diff master..GitNewBranch
# Shows line-by-line differences of all files

# STEP 9: Visual diff with P4Merge
git difftool master..GitNewBranch
# Opens P4Merge with side-by-side visual comparison

# STEP 10: Merge GitNewBranch into master (fast-forward merge)
git merge GitNewBranch
# Output: Updating <hash>..<hash>
# Fast-forward
#  branch-config.txt | 1 +
#  feature-a.txt     | 1 +
#  feature-b.txt     | 1 +

# STEP 11: Observe graph log after merge
git log --oneline --graph --decorate
# Output:
# * <hash> (HEAD -> master, GitNewBranch) Add feature files to GitNewBranch
# * <hash> Add welcome.txt - initial commit for GitDemo project

# STEP 12: Delete branch after successful merge
git branch -d GitNewBranch
# Output: Deleted branch GitNewBranch (was <hash>).

# Verify branch deleted
git branch -a
# Should show only master (and remote branches)

# Final status check
git status
# On branch master
# nothing to commit, working tree clean
