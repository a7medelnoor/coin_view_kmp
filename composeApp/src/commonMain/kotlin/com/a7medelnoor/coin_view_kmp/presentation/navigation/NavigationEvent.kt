sealed class NavigationEvent {
    data class NavigateToDetails(val cryptoId: String) : NavigationEvent()
    data object NavigateBack : NavigationEvent()
} 