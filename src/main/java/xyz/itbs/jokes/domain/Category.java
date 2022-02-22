package xyz.itbs.jokes.domain;

public enum Category {

    ANIMAL{
        @Override
        public String toString(){
            return "Animal";
        }
    },
    BLONDE{
        @Override
        public String toString(){
            return "Blonde";
        }
    },
    KNOCK_KNOCK{
        @Override
        public String toString(){
            return "Knock-Knock";
        }
    },
    JOD{
        @Override
        public String toString(){
            return "Joke of the Day";
        }
    },
    OTHER{
        @Override
        public String toString(){
            return "Other";
        }
    }
}
