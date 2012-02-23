package org.jenkinsci.plugins.openjdk_native;

import hudson.Extension;
import hudson.MarkupText;
import hudson.console.ConsoleAnnotationDescriptor;
import hudson.console.ConsoleAnnotator;
import hudson.console.ConsoleNote;

public class OpenJDKErrorNote extends ConsoleNote {
    
    @Override
    public ConsoleAnnotator annotate(Object context, MarkupText text, int charPos){
        if(text.getText().contains("ERROR"))
            text.addMarkup(0,text.length(),"<span style=\"font-weight: bold; color:red\">","</span>");
        return null;
    }
    
    @Extension
    public static final class DescriptorImpl extends ConsoleAnnotationDescriptor {
        public String getDisplayName() {
            return "OpenJDK installer errors";
        }
    }

}
