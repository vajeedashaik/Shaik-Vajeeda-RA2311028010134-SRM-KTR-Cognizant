Objectives
Explain the need and benefit of ignoring files in Git
Explain the .gitignore file and its pattern syntax
In this hands-on lab, you will learn how to:
Create files that should not be tracked by Git, such as log files
Create a .gitignore file with appropriate patterns to ignore those files
Verify that the ignored files no longer show up as untracked in git status
Commit the .gitignore file and push it to the remote repository
Prerequisites
The following are the pre-requisites to complete this hands-on lab:
Hands-on ID: "Git-T02-HOL_001"
The "GitDemo" local repository created in "Git-T01-HOL_001", connected to a remote on GitLab
Notes*:
Estimated time to complete this lab: 20 minutes.
Please follow the instructions to complete the hands-on. Each instruction expects a command for the Git Bash.
Create a file named "app.log" and a file named "error.log" in the root of the repository.
Create a folder named "logs" containing the files "server.log" and "debug.log".
Run git status and observe that all of the above files are listed as untracked.
Create a .gitignore file that ignores all files with a ".log" extension and everything inside any "logs" folder.
Run git status again and verify that the log files and the logs folder no longer appear as untracked.
Use git check-ignore -v to verify which rule in .gitignore is causing each file to be ignored.
Stage and commit only the .gitignore file with an appropriate commit message.
Push the commit to the remote repository and verify it on GitLab.
