Objectives
Explain the concept of branching in Git
Explain how to merge changes from one branch into another
Explain how to configure and use an external diff/merge tool
In this hands-on lab, you will learn how to:
Configure P4Merge as the default diff and merge tool for Git
Create a new branch and switch to it
Add and commit changes on the new branch
Compare changes between branches using diff and difftool
Merge the new branch back into master
View the commit history as a graph
Delete a branch once it has been merged
Prerequisites
The following are the pre-requisites to complete this hands-on lab:
Hands-on ID: "Git-T03-HOL_001"
P4Merge installed
The "GitDemo" local repository created in the previous hands-on labs
Notes*:
Estimated time to complete this lab: 40 minutes.
Please follow the instructions to complete the hands-on. Each instruction expects a command for the Git Bash.
Configure P4Merge as the default diff tool and merge tool for Git.
Create a new branch named "GitNewBranch" and list all the available branches.
Switch to "GitNewBranch", add new files, and commit them.
Switch back to "master" and compare "master" with "GitNewBranch" using both git diff and git difftool.
Merge "GitNewBranch" into "master" and verify that it results in a fast-forward merge.
View the commit history as a graph using git log with the --graph, --oneline and --decorate options.
Delete the "GitNewBranch" branch once it has been merged, and verify it no longer appears in the branch list.
