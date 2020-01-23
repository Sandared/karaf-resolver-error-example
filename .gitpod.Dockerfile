FROM gitpod/workspace-full:latest

# Install Maven bash completion
# Download Karaf and the agent
USER gitpod
RUN wget -O .mvn_bash_completion https://raw.githubusercontent.com/natros/maven-bash-completion/master/bash_completion.bash \
&& echo 'source ~/.mvn_bash_completion' >> ~/.bashrc
# Set path to include Karaf that will be downloaded during the init task
ENV PATH="/workspace/apache-karaf-4.2.7/bin/:$PATH"
# return control
USER root