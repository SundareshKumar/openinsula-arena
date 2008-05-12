function somenteNumero(numero) {
	// numeros aceitos 0,1,2,3,4,5,6,7,8,9,37,38,39,40,46
	ER=/(^[0-2]$|^3[789]{0,1}$|^4[06]{0,1}$|^[5-9]$)/
	return ER.test(numero)
}

function formataInteiro(dado) {
	var aux = "";
	for(n=0; n < dado.length; n++){
		if(somenteNumero(dado.substr(n,1))){
			aux += dado.substr(n,1);
		}
	}
	
	return aux;
}

/***
*  Função genérica capaz de realizar vários formatos, bastando apenas indicar qual
*  o formato desejado no parâmetro "mascara".
*  Parâmetros:
*      - obj     : objeto que contém o valor a ser formato. Quando a função for 
*                  chamada diretamente de um evento no próprio objeto, o parâmetro
*                  poderá conter valor 0 (zero). 
*      - evt     : evento
*      - mascara : indica como deverá ser a formatação. Exemplo: para formatar um
*                  CEP, use a máscara: "99999-999" ; ou telefone: "(99)999-9999" 
***/
function formataNumberMask(valor, mascara) {
	var tamValor = 0;
	var tamMascara = 0;
	var resultado = "";
	var valorAux = "";
	var mascaraAux = "";
	var posMas = 1;
	var posVal = 1;
	var masclen = mascara.length;

	if(valor.length > mascara.length) {
		valor = formataInteiro(valor.substring(0, valor.length-(valor.length-mascara.length)));
	}
	else {
		valor = formataInteiro(valor);
	}
	if(valor!="") {
		tamValor = valor.length;
		tamMascara = mascara.length;
		while((posVal <= tamValor) && (posMas <= tamMascara)) {
			valorAux = valor.substring(tamValor - posVal, (tamValor - posVal) + 1);
			mascaraAux = mascara.substring(tamMascara - posMas, (tamMascara - posMas) + 1);
			if(mascaraAux == "9") {	
				resultado = valorAux + resultado;
				posVal = posVal + 1;
			}
			else if((mascaraAux == "-") || (mascaraAux == "/") || (mascaraAux == "." ) || (mascaraAux == "(") || (mascaraAux == ")") || (mascaraAux == ",")) {
				resultado = mascaraAux + resultado;
			}
			posMas = posMas + 1;
		}
		posVal -= 1;
		posMas -= 1;
		if((posMas == tamMascara - 1) && (mascara.substring(0, 1) == "(")) {
			resultado = "(" + resultado;
		}
	}
	
	return resultado;
}

EchoNumberMaskField = function(elementId) {
    this.elementId = elementId;
};

EchoNumberMaskField.getComponent = function(element) {
    return EchoDomPropertyStore.getPropertyValue(element, "component");
};

EchoNumberMaskField.prototype.getElement = function() {
    return document.getElementById(this.elementId);
};

EchoNumberMaskField.prototype.dispose = function() {
    var element = this.getElement();
    EchoEventProcessor.removeHandler(element, "blur");
    EchoEventProcessor.removeHandler(element, "focus");
    EchoEventProcessor.removeHandler(element, "keyup");
    EchoDomUtil.removeEventListener(element, "keypress", EchoNumberMaskField.processKeyPress, false);    

    // Remove any updates to text component that occurred during client/server transaction.
    EchoClientMessage.removePropertyElement(element.id, "text");
    
    EchoDomPropertyStore.dispose(element);
};

EchoNumberMaskField.prototype.init = function() {
    var element = this.getElement();
    
    if (!this.enabled) {
        element.readOnly = true;
    }
    if (this.text) {
        element.value = this.text;
    }

    if (this.horizontalScroll != 0) {
        element.scrollLeft = this.horizontalScroll;
    }
    
    if (this.verticalScroll != 0) {
        if (EchoClientProperties.get("quirkIERepaint")) {
            // Avoid IE quirk where browser will fail to set scroll bar position.
            var originalWidth = element.style.width;
            var temporaryWidth = parseInt(element.clientWidth) - 1;
            element.style.width = temporaryWidth + "px";
            element.style.width = originalWidth;
        }
        element.scrollTop = this.verticalScroll;
    }
    
    if (EchoClientProperties.get("quirkMozillaTextInputRepaint")) {
        // Avoid Mozilla quirk where text will be rendered outside of text field
        // (this appears to be a Mozilla bug).
        var noValue = !element.value;
        if (noValue) {
            element.value = "-";
        }
        var currentWidth = element.style.width;
        element.style.width = "20px";
        element.style.width = currentWidth;
        if (noValue) {
            element.value = "";
        }
    }
    
    EchoEventProcessor.addHandler(element, "blur", "EchoNumberMaskField.processBlur");
    EchoEventProcessor.addHandler(element, "focus", "EchoNumberMaskField.processFocus");
    EchoEventProcessor.addHandler(element, "keyup", "EchoNumberMaskField.processKeyUp");
    
    EchoDomUtil.addEventListener(element, "keypress", EchoNumberMaskField.processKeyPress, false);
    
    EchoDomPropertyStore.setPropertyValue(element, "component", this);
};

EchoNumberMaskField.prototype.doAction = function() {
	var element = this.getElement();

    if (!this.serverNotify) {
        return;
    }
    
    if (!this.enabled || !EchoClientEngine.verifyInput(this.getElement(), false)) {
        // Don't process actions when client/server transaction in progress.
        EchoDomUtil.preventEventDefault(echoEvent);
        
        return;
    }
    
    EchoClientMessage.setActionValue(this.elementId, "action");
    EchoServerTransaction.connect();
};

EchoNumberMaskField.prototype.processFocus = function(echoEvent) {
    if (!this.enabled || !EchoClientEngine.verifyInput(this.getElement())) {
        return;
    }
    
    EchoFocusManager.setFocusedState(this.elementId, true);
};

EchoNumberMaskField.prototype.processBlur = function(echoEvent) {
	var numberMaskFieldHidden = document.getElementById(this.elementId + "_hidden");
	var element = this.getElement();
    
    if (!this.enabled || !EchoClientEngine.verifyInput(this.getElement())) {
        return;
    }
    
    EchoFocusManager.setFocusedState(this.elementId, false);
	
    element.value = formataNumberMask(element.value, numberMaskFieldHidden.value)
    
    this.doAction();
};

EchoNumberMaskField.prototype.processKeyPress = function(e) {
    if (!this.enabled || !EchoClientEngine.verifyInput(this.getElement(), true)) {
        EchoDomUtil.preventEventDefault(e);
        return;
    }
    
    if (e.keyCode == 13) {
        this.doAction();
    }
};

EchoNumberMaskField.prototype.processKeyUp = function(echoEvent) {
    var element = this.getElement();
    var numberMaskFieldHidden = document.getElementById(this.elementId + "_hidden");
    
    if (!this.enabled || !EchoClientEngine.verifyInput(element, true)) {
        EchoDomUtil.preventEventDefault(echoEvent);
        return;
    }
    
    if (this.maximumLength >= 0) {
        if (element.value && element.value.length > this.maximumLength) {
            element.value = element.value.substring(0, this.maximumLength);
        }
    }
    
    element.value = formataNumberMask(element.value, numberMaskFieldHidden.value)
    
    this.updateClientMessage();
};

EchoNumberMaskField.prototype.updateClientMessage = function() {
    var element = this.getElement();
    var textPropertyElement = EchoClientMessage.createPropertyElement(this.elementId, "text");
    
    if (textPropertyElement.firstChild) {
        textPropertyElement.firstChild.nodeValue = element.value;
    } else {
        textPropertyElement.appendChild(EchoClientMessage.messageDocument.createTextNode(element.value));
    }
    
    EchoClientMessage.setPropertyValue(this.elementId, "horizontalScroll", element.scrollLeft);
    EchoClientMessage.setPropertyValue(this.elementId, "verticalScroll", element.scrollTop);
};

EchoNumberMaskField.MessageProcessor = function() { };

EchoNumberMaskField.MessageProcessor.process = function(messagePartElement) {
    for (var i = 0; i < messagePartElement.childNodes.length; ++i) {
        if (messagePartElement.childNodes[i].nodeType == 1) {
            switch (messagePartElement.childNodes[i].tagName) {
            case "init":
                EchoNumberMaskField.MessageProcessor.processInit(messagePartElement.childNodes[i]);
                break;
            case "dispose":
                EchoNumberMaskField.MessageProcessor.processDispose(messagePartElement.childNodes[i]);
                break;
            case "set-text":
                EchoNumberMaskField.MessageProcessor.processSetText(messagePartElement.childNodes[i]);
                break;
            }
        }
    }
};

EchoNumberMaskField.MessageProcessor.processDispose = function(disposeMessageElement) {
    for (var item = disposeMessageElement.firstChild; item; item = item.nextSibling) {
        var elementId = item.getAttribute("eid");
        var textComponent = EchoNumberMaskField.getComponent(elementId);
        if (textComponent) {
            textComponent.dispose();
        }
    }
};

EchoNumberMaskField.MessageProcessor.processSetText = function(setTextMessageElement) {
    for (var item = setTextMessageElement.firstChild; item; item = item.nextSibling) {
        var elementId = item.getAttribute("eid");
        var text = item.getAttribute("text");
        var textComponent = document.getElementById(elementId);
        textComponent.value = text;
        
        // Remove any updates to text component that occurred during client/server transaction.
        EchoClientMessage.removePropertyElement(textComponent.id, "text");
    }
};

EchoNumberMaskField.MessageProcessor.processInit = function(initMessageElement) {
    for (var item = initMessageElement.firstChild; item; item = item.nextSibling) {
        var elementId = item.getAttribute("eid");
        
        var textComponent = new EchoNumberMaskField(elementId);
        textComponent.enabled = item.getAttribute("enabled") != "false";
        textComponent.text =  item.getAttribute("text") ? item.getAttribute("text") : null;
        textComponent.serverNotify = item.getAttribute("server-notify") == "true";
        textComponent.maximumLength = item.getAttribute("maximum-length") ? item.getAttribute("maximum-length") : -1;
        textComponent.horizontalScroll = item.getAttribute("horizontal-scroll") ? parseInt(item.getAttribute("horizontal-scroll")) : 0;
        textComponent.verticalScroll = item.getAttribute("vertical-scroll") ? parseInt(item.getAttribute("vertical-scroll")) : 0;
                
        textComponent.init();
    }
};

EchoNumberMaskField.processBlur = function(echoEvent) {
    var textComponent = EchoNumberMaskField.getComponent(echoEvent.registeredTarget);
    textComponent.processBlur(echoEvent);
};

EchoNumberMaskField.processFocus = function(echoEvent) {
    var textComponent = EchoNumberMaskField.getComponent(echoEvent.registeredTarget);
    textComponent.processFocus(echoEvent);
};

EchoNumberMaskField.processKeyPress = function(e) {
    e = e ? e : window.event;
    var target = EchoDomUtil.getEventTarget(e);
    var textComponent = EchoNumberMaskField.getComponent(target);
    textComponent.processKeyPress(e);
};

EchoNumberMaskField.processKeyUp = function(echoEvent) {
    var textComponent = EchoNumberMaskField.getComponent(echoEvent.registeredTarget);
    textComponent.processKeyUp(echoEvent);
};
