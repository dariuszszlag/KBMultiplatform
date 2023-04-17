import SwiftUI
import kbcore

struct ContentView: View {

    let builder = KBCoreIOSBuilder()
        .coroutineDispatcher(coroutineDispatcher: <#T##Kotlinx_coroutines_coreCoroutineDispatcher#>)
        .build()
    
	var body: some View {
		Text("Hello")
	}
    
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
