#!/bin/bash
# Exercise 1: Git Setup, Notepad++ integration, First commit, Push to GitLab

# ─── STEP 1: Verify Git installation ───────────────────────────────────────
git --version
# Expected output: git version 2.x.x

# ─── STEP 2: Configure user identity (user-level) ──────────────────────────
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Verify configuration
git config --list

# ─── STEP 3: Integrate Notepad++ as default Git editor ─────────────────────
# First verify notepad++ is accessible from Git Bash:
notepad++
# If not found, add its path to Windows Environment Variables (PATH):
#   Control Panel → System → Advanced System Settings → Environment Variables
#   Add: C:\Program Files\Notepad++ to Path variable

# Create alias for notepad++
git config --global alias.npp '!notepad++'

# Set notepad++ as default Git editor
git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"

# Add to ~/.bash_profile for shell alias
echo "alias notepad++='\"C:/Program Files/Notepad++/notepad++.exe\"'" >> ~/.bash_profile

# Verify editor is set (opens .gitconfig in the configured editor)
git config -e --global
# -e flag opens the global config in the configured editor

# ─── STEP 4: Create local repository 'GitDemo' ─────────────────────────────
mkdir GitDemo
cd GitDemo
git init

# Verify .git directory created
ls -la
# Should show .git hidden folder

# ─── STEP 5: Create and add a file ─────────────────────────────────────────
echo "Welcome to Git Demo" > welcome.txt

# Verify file creation
ls
cat welcome.txt

# Check status — file is untracked (in working directory, not staged)
git status

# Stage the file (move to staging area)
git add welcome.txt

# Check status — file is now staged (green)
git status

# ─── STEP 6: Commit with multi-line comment via editor ─────────────────────
# This opens Notepad++ for multi-line commit message
git commit

# OR commit directly with message:
git commit -m "Add welcome.txt - initial commit for GitDemo project"

# Verify commit
git status
# Should show "nothing to commit, working tree clean"

# ─── STEP 7: Connect to GitLab remote and push ─────────────────────────────
# First: Signup at gitlab.com, create project named 'GitDemo'

# Add remote origin
git remote add origin https://gitlab.com/<your-username>/GitDemo.git

# Pull remote (in case GitLab created README)
git pull origin master

# Push local commits to remote
git push origin master

# Verify on GitLab UI — welcome.txt should appear in the repository
