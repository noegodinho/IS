import generated.ListCourses;

def common = []
subs = flowVars['materials'];
subsNew = flowVars['materialsNew'];

       
subs.getCourses().each{s ->
    subsNew.getCourses().each{sNew ->
        if(s.getId() == sNew.getId()){
            def idArray = []
            def idArrayNew = []

            s.getMaterials().each{ s2 ->
                idArray << s2.getId()
            }

            sNew.getMaterials().each{ s2 ->
                idArrayNew << s2.getId()
            }

            def difference = idArray.plus(idArrayNew)
            difference.removeAll(idArray.intersect(idArrayNew))

            difference.each{ dif ->
                def tempList = []

                s.getMaterials().each{ s2 -> 
                    if(dif == s2.getId()){
                        tempList << s2.getFilename()
                    }
                }

                sNew.getMaterials().each{ s2 -> 
                    if(dif == s2.getId()){
                        tempList << s2.getFilename()
                    }
                }

                int index = 0

                common.each{ com ->
                    if(com[0] == s.getCourseName()){
                        com[1] += tempList
                        com[1] = com[1].flatten()
                        index = 1
                    }
                }

                if(index == 0){
                    common << [s.getCourseName(), tempList]
                }
            }
        }
    }
}
       
message.setInvocationProperty('results',common);