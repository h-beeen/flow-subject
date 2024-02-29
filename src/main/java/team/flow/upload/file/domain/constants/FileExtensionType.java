package team.flow.upload.file.domain.constants;

public enum FileExtensionType {
    BAT,
    CMD,
    COM,
    CPL,
    EXE,
    SCR,
    JS;

    public String getLowerCase() {
        return this.toString().toLowerCase();
    }
}
