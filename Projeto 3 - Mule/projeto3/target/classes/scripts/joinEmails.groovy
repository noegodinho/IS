def mailContainer = flowVars['mailContainer']
def email = flowVars['email']
def body = payload.toString()
int index = 0

mailContainer.each{ mc ->
	if(mc[0] == email){
	    mc[1] += body
	    mc[1] = mc[1].flatten()
	    index = 1
	}

}

if(index == 0){
	mailContainer << [email, [body]]
}

message.setInvocationProperty('mailContainer', mailContainer)