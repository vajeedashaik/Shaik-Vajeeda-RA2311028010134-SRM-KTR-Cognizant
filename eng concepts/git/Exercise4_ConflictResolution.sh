#!/bin/bash
# Exercise 4: Merge conflict resolution using 3-way merge (P4Merge)

cd GitDemo

# ─── STEP 1: Verify master is in clean state ───────────────────────────────
git status
# On branch master
# nothing to commit, working tree clean

# ─── STEP 2: Create branch 'GitWork' and add hello.xml ─────────────────────
git branch GitWork
git checkout GitWork

# Add hello.xml with content for the branch
cat > hello.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<greetings>
    <message>Hello from GitWork branch!</message>
    <author>Branch Developer</author>
</greetings>
EOF

git add hello.xml

# ─── STEP 3: Update hello.xml and observe status ───────────────────────────
# Modify the file further
cat > hello.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<greetings>
    <message>Hello from GitWork branch - updated!</message>
    <author>Branch Developer</author>
    <version>1.1</version>
</greetings>
EOF

git status
# Changes not staged for commit: modified: hello.xml

# ─── STEP 4: Commit changes to branch ──────────────────────────────────────
git add hello.xml
git commit -m "Add hello.xml with branch-specific greeting"

# ─── STEP 5: Switch to master ──────────────────────────────────────────────
git checkout master

# ─── STEP 6: Add hello.xml to master with DIFFERENT content (creates conflict) ──
cat > hello.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<greetings>
    <message>Hello from Master branch!</message>
    <author>Master Developer</author>
    <version>2.0</version>
</greetings>
EOF

# ─── STEP 7: Commit the master version of hello.xml ────────────────────────
git add hello.xml
git commit -m "Add hello.xml with master-specific greeting"

# ─── STEP 8: Observe diverged log ──────────────────────────────────────────
git log --oneline --graph --decorate --all
# Output (diverged history):
# * <hash> (HEAD -> master) Add hello.xml with master-specific greeting
# | * <hash> (GitWork) Add hello.xml with branch-specific greeting
# |/
# * <hash> Add welcome.txt - initial commit

# ─── STEP 9: CLI diff between master and GitWork ───────────────────────────
git diff master..GitWork
# Shows conflicting lines between both versions

# ─── STEP 10: Visual diff with P4Merge ─────────────────────────────────────
git difftool master..GitWork
# P4Merge opens showing both versions side-by-side

# ─── STEP 11: Attempt to merge — CONFLICT occurs ───────────────────────────
git merge GitWork
# Output:
# Auto-merging hello.xml
# CONFLICT (add/add): Merge conflict in hello.xml
# Automatic merge failed; fix conflicts and then commit the result.

# ─── STEP 12: Observe Git conflict markup in hello.xml ─────────────────────
cat hello.xml
# Output:
# <?xml version="1.0" encoding="UTF-8"?>
# <greetings>
# <<<<<<< HEAD
#     <message>Hello from Master branch!</message>
#     <author>Master Developer</author>
#     <version>2.0</version>
# =======
#     <message>Hello from GitWork branch - updated!</message>
#     <author>Branch Developer</author>
#     <version>1.1</version>
# >>>>>>> GitWork
# </greetings>
# <<<<<<< HEAD  = current master content
# ======= = separator
# >>>>>>> GitWork = incoming branch content

# ─── STEP 13: Resolve with P4Merge 3-way merge tool ───────────────────────
git mergetool
# P4Merge opens with 3 panels:
#   Left  = LOCAL (master)
#   Center = BASE (common ancestor)
#   Right  = REMOTE (GitWork)
#   Bottom = MERGED (your resolution)
# Pick/edit lines in bottom panel, save and close P4Merge

# After resolving, hello.xml should contain the merged content:
cat > hello.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<greetings>
    <message>Hello from both Master and GitWork - Resolved!</message>
    <author>Both Developers</author>
    <version>2.1</version>
</greetings>
EOF

# ─── STEP 14: Commit the resolved conflict ──────────────────────────────────
git add hello.xml
git commit -m "Resolve merge conflict between master and GitWork in hello.xml"

# ─── STEP 15: Add P4Merge backup file to .gitignore ───────────────────────
# P4Merge creates .orig backup files
echo "*.orig" >> .gitignore

git status
# Shows .gitignore as modified

git add .gitignore
git commit -m "Add *.orig to .gitignore (P4Merge backup files)"

# ─── STEP 16: List all branches ────────────────────────────────────────────
git branch -a

# ─── STEP 17: Delete merged branch ─────────────────────────────────────────
git branch -d GitWork
# Output: Deleted branch GitWork (was <hash>).

# ─── STEP 18: Final log ─────────────────────────────────────────────────────
git log --oneline --graph --decorate
# Output shows merge commit connecting the two diverged paths:
# *   <hash> (HEAD -> master) Resolve merge conflict between master and GitWork
# |\
# | * <hash> Add hello.xml with branch-specific greeting
# * | <hash> Add hello.xml with master-specific greeting
# |/
# * <hash> Add welcome.txt - initial commit
