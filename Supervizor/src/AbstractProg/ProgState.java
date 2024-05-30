package AbstractProg;

/**
 * Enumeration type of abstract program states
 * UNKNOWN – before the first launch
 * STOPPING - stopped
 * RUNNING - working
 * FATAL ERROR - a critical error
 */
public enum ProgState {
    UNKNOWN,
    STOPPING,
    RUNNING,
    FATAL_ERROR
}
