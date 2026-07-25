Objectives
Explain what a merge conflict is and why it occurs
Explain how to resolve a merge conflict using a 3-way merge tool
In this hands-on lab, you will learn how to:
Create a scenario where the same file is added with different content on two branches
Attempt to merge the branches and observe the resulting merge conflict
Understand the conflict markers that Git adds inside the conflicted file
Resolve the conflict using P4Merge (LOCAL, BASE, REMOTE and MERGED panels)
Commit the resolved file and clean up the merged branch
Prerequisites
The following are the pre-requisites to complete this hands-on lab:
Hands-on ID: "Git-T03-HOL_002"
P4Merge installed and configured as the merge tool
The "GitDemo" local repository created in the previous hands-on labs
Notes*:
Estimated time to complete this lab: 40 minutes.
Please follow the instructions to complete the hands-on. Each instruction expects a command for the Git Bash.
Verify that "master" is in a clean state.
Create a branch named "GitWork", add a file named "hello.xml" with some content, and commit it.
Switch back to "master" and add a file with the same name "hello.xml" but with different content, then commit it.
Observe the diverged commit history using git log --graph --all.
Compare the two branches using git diff and git difftool.
Merge "GitWork" into "master" and observe the merge conflict that is raised.
Open the conflicted file and identify the conflict markers added by Git.
Resolve the conflict using git mergetool (P4Merge) and save the merged result.
Stage and commit the resolved file.
Add the merge tool's backup file pattern (*.orig) to .gitignore and commit it.
List all branches, delete the "GitWork" branch since it is now fully merged, and view the final commit graph.
